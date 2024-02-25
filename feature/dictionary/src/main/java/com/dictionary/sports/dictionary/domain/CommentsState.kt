package com.dictionary.sports.dictionary.domain

sealed class CommentsState {
    object Loading : CommentsState()

    object Success : CommentsState()

    data class Error(val messageRes: Int) : CommentsState()
}