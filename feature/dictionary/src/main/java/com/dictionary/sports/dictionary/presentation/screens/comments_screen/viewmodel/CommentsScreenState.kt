package com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel

import com.dictionary.sports.dictionary.domain.model.Comment

data class CommentsScreenState(
    val isLogged: Boolean = false,
    val commentText: String = "",
    val commentsState: CommentsState = CommentsState.Loading
)

sealed interface CommentsState {
    object Loading : CommentsState

    data class Success(
        val comments: List<Comment> = emptyList(),
    ): CommentsState

    data class Error(val message : String): CommentsState
}