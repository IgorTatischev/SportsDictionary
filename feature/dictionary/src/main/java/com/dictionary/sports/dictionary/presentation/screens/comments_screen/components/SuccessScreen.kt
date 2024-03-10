package com.dictionary.sports.dictionary.presentation.screens.comments_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.CommentsState

@Composable
internal fun SuccessScreen(
    state: CommentsState.Success
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = state.comments,
            key = { comment ->
                comment.id
            }
        ) { comment ->
            if (state.comments.isNotEmpty()) {
                when (comment) {
                    state.comments.last() -> {
                        CommentText(
                            comment = comment,
                            modifier = Modifier.padding(bottom = 120.dp)
                        )
                    }

                    else -> CommentText(comment = comment)
                }
            }
        }
    }
}