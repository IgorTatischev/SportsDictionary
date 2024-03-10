package com.dictionary.sports.dictionary.presentation.screens.comments_screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.dictionary.domain.repository.SupabaseComments
import com.dictionary.sports.supabase.repository.SupabaseRepository
import com.dictionary.sports.supabase.state.LoggedInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CommentsScreenModel(
    private val supabaseComments: SupabaseComments,
    private val supabaseRepository: SupabaseRepository,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository,
) : ScreenModel {

    private var token = ""

    private val _state = MutableStateFlow(CommentsScreenState())
    val state: StateFlow<CommentsScreenState> = _state.asStateFlow()

    init {
        getToken()
    }

    fun changeCommentText(newValue: String) =
        _state.update { it.copy(commentText = newValue) }

    private fun clearCommentText() = _state.update { it.copy(commentText = "") }

    fun getComments(filterValue: Int) {
        screenModelScope.launch(Dispatchers.IO) {
            supabaseComments.getComments(filterValue)
                .onSuccess { flow ->
                    flow.onEach { comments ->
                        _state.update {
                            it.copy(commentsState = CommentsState.Success(comments = comments))
                        }
                    }.collect()
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            commentsState = CommentsState.Error(throwable.message.toString()),
                        )
                    }
                }
        }
    }

    fun createComment(filterValue: Int) = with(_state.value) {
        screenModelScope.launch(Dispatchers.IO) {
            supabaseComments.createComment(
                commentText = commentText,
                commentFor = filterValue
            ).onFailure { throwable ->
                _state.update {
                    it.copy(
                        commentsState = CommentsState.Error(throwable.message.toString()),
                    )
                }
            }
        }
        clearCommentText()
    }

    fun isUserLoggedIn() {
        screenModelScope.launch(Dispatchers.IO) {
            when (val result = supabaseRepository.isUserLoggedIn(token)) {

                is LoggedInState.Error -> _state.update { it.copy(isLogged = false) }

                is LoggedInState.Success -> _state.update { it.copy(isLogged = result.isLoggedIn) }
            }
        }
    }

    fun leaveComments() = screenModelScope.launch { supabaseComments.leaveChannel() }

    private fun getToken() {
        dataStorePreferencesRepository.getToken().onEach {
            token = it
        }.launchIn(screenModelScope)
    }
}