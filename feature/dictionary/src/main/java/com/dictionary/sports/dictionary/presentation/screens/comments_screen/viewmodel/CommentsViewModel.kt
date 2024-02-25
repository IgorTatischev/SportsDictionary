package com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.supabase.repository.SupabaseRepository
import com.dictionary.sports.common.supabase.state.LoggedInState
import com.dictionary.sports.dictionary.domain.CommentsState
import com.dictionary.sports.dictionary.domain.repository.SupabaseDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.dictionary.sports.resources.R as CoreRes

class CommentsViewModel(
    private val supabaseDBRepository: SupabaseDBRepository,
    private val supabaseRepository: SupabaseRepository,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) : ScreenModel {
    private val token = mutableStateOf("")

    private val _commentsFeatureState = MutableStateFlow(
        CommentsFeatureState(
            "",
            emptyList(),
            CommentsState.Loading,
            false
        )
    )
    val commentsFeatureState: StateFlow<CommentsFeatureState> = _commentsFeatureState.asStateFlow()


    init {
        getToken()
    }

    fun changeCommentText(newValue: String) {
        _commentsFeatureState.value = _commentsFeatureState.value.copy(commentText = newValue)
    }

    private fun clearCommentText() {
        _commentsFeatureState.value = _commentsFeatureState.value.copy(commentText = "")
    }

    fun getComments(filterValue: Int) {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                _commentsFeatureState.value =
                    _commentsFeatureState.value.copy(
                        comments = supabaseDBRepository.getComments(
                            filterValue
                        )
                    )

                _commentsFeatureState.value =
                    _commentsFeatureState.value.copy(commentState = CommentsState.Success)
            } catch (e: Exception) {
                _commentsFeatureState.value =
                    _commentsFeatureState.value.copy(commentState = CommentsState.Error(CoreRes.string.error))
            }
        }
    }

    fun createComment(filterValue: Int) {
        screenModelScope.launch(Dispatchers.IO) {
            val result = supabaseDBRepository.createComment(
                commentText = _commentsFeatureState.value.commentText,
                commentFor = filterValue
            )

            when {
                result is CommentsState.Success -> {
                    clearCommentText()
                    getComments(filterValue = filterValue)
                }

                result is CommentsState.Error -> _commentsFeatureState.value =
                    _commentsFeatureState.value.copy(commentState = CommentsState.Error(result.messageRes))
            }
        }
    }

    fun isUserLoggedIn() {
        screenModelScope.launch(Dispatchers.IO) {
            val result = supabaseRepository.isUserLoggedIn(token.value)
            when (result) {
                is LoggedInState.Success -> _commentsFeatureState.value =
                    _commentsFeatureState.value.copy(isLogged = result.isLoggedIn)

                is LoggedInState.Error -> _commentsFeatureState.value =
                    _commentsFeatureState.value.copy(commentState = CommentsState.Error(CoreRes.string.error))
            }
        }
    }

    private fun getToken() {
        dataStorePreferencesRepository.getToken().onEach {
            token.value = it
        }.launchIn(screenModelScope)
    }
}