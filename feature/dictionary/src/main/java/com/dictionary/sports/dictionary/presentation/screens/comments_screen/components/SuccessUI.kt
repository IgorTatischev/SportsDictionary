package com.dictionary.sports.dictionary.presentation.screens.comments_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel

@Composable
fun BoxScope.SuccessUI(
    commentsViewModel: CommentsViewModel
) {
    val comments = commentsViewModel.commentsFeatureState.collectAsState().value.comments

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .align(Alignment.TopCenter),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = comments,
            key = { comment ->
                comment.id
            }
        ) { comment ->
            if (comments.isNotEmpty()) {
                when (comment) {
                    comments.last() -> {
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