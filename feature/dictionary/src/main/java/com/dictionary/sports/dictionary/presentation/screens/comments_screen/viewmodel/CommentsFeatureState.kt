package com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel

import com.dictionary.sports.dictionary.domain.CommentsState
import com.dictionary.sports.dictionary.domain.model.Comment

data class CommentsFeatureState(
    val commentText: String,
    val comments: List<Comment>,
    val commentState: CommentsState,
    val isLogged: Boolean
)
