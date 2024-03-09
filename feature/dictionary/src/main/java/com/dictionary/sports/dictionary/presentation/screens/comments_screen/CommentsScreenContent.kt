package com.dictionary.sports.dictionary.presentation.screens.comments_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.components.CommentButton
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.components.CommentTextField
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.components.CommentsTopBar
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.components.SuccessScreen
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsState
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel
import com.dictionary.sports.ui.components.ErrorScreen
import com.dictionary.sports.ui.components.backgroundGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreenContent(
    filterValue: Int,
    commentsViewModel: CommentsViewModel,
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current
    val width = context.resources.displayMetrics.widthPixels
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CommentsTopBar(scrollBehavior = scrollBehavior) {
                navigateBack()
            }
        },
    ) { padding ->

        Box(
            Modifier
                .fillMaxSize()
                .backgroundGradient(width.toFloat())
        )

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            val state = commentsViewModel.state.collectAsState().value

            LaunchedEffect(Unit) {
                commentsViewModel.getComments(filterValue = filterValue)
            }

            when (state.commentsState) {

                is CommentsState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .align(Alignment.TopCenter),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                is CommentsState.Success -> SuccessScreen(state = state.commentsState)

                is CommentsState.Error -> ErrorScreen(error = state.commentsState.message)
            }

            if (state.isLogged)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        )
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CommentTextField(
                        value = state.commentText,
                        onValueChange = { commentsViewModel.changeCommentText(newValue = it) }
                    )

                    CommentButton {
                        commentsViewModel.createComment(filterValue = filterValue)
                    }
                }
        }
    }
}