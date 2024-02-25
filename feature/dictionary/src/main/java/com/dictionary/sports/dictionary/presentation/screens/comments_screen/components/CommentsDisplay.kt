package com.dictionary.sports.dictionary.presentation.screens.comments_screen.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dictionary.sports.dictionary.domain.CommentsState
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel

@Composable
fun BoxScope.CommentsDisplay(
    commentsViewModel: CommentsViewModel,
    filterValue: Int
) {
    val commentState = commentsViewModel.commentsFeatureState.collectAsState().value.commentState

    LaunchedEffect(key1 = Unit) {
        commentsViewModel.isUserLoggedIn()
        commentsViewModel.getComments(filterValue = filterValue)
        commentsViewModel.isUserLoggedIn()
    }

    when (commentState) {
        is CommentsState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.TopCenter),
                color = MaterialTheme.colorScheme.primary
            )
        }

        is CommentsState.Success -> {
            SuccessUI(commentsViewModel = commentsViewModel)
        }

        is CommentsState.Error -> {
            ErrorUI(messageRes = commentState.messageRes)
        }
    }
}