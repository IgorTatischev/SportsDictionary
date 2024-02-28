package com.dictionary.sports.dictionary.presentation.screens.comments_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.components.CommentAddDisplay
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.components.CommentsDisplay
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.components.CommentsTopBar
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel
import com.dictionary.sports.ui.components.backgroundGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreenUi(
    filterValue: Int,
    commentsViewModel: CommentsViewModel,
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels
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
            CommentsDisplay(
                commentsViewModel = commentsViewModel,
                filterValue = filterValue
            )

            if (commentsViewModel.commentsFeatureState.collectAsState().value.isLogged)
                CommentAddDisplay(
                    commentsViewModel = commentsViewModel,
                    filterValue = filterValue
                )
        }
    }
}