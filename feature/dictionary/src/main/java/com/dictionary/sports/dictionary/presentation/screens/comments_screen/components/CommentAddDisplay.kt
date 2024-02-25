package com.dictionary.sports.dictionary.presentation.screens.comments_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel

@Composable
fun BoxScope.CommentAddDisplay(
    commentsViewModel: CommentsViewModel,
    filterValue: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        MaterialTheme.colorScheme.primaryContainer
                    )
                )
            )
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommentTextField(commentsViewModel = commentsViewModel)

        CommentButton(
            commentsViewModel = commentsViewModel,
            filterValue = filterValue
        )
    }
}