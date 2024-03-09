package com.dictionary.sports.dictionary.presentation.screens.comments_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsState
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel

@Composable
fun SuccessScreen(
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
                        Comment(
                            comment = comment,
                            modifier = Modifier.padding(bottom = 120.dp)
                        )
                    }

                    else -> Comment(comment = comment)
                }
            }
        }
    }
}