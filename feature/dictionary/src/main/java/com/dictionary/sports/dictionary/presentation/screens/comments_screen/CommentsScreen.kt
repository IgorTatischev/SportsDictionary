package com.dictionary.sports.dictionary.presentation.screens.comments_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel


data class CommentsScreen(val filterValue: Int) : Screen {
    @Composable
    override fun Content() {

        val commentsViewModel = getScreenModel<CommentsViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(true) {
            commentsViewModel.isUserLoggedIn()
        }

        CommentsScreenUi(
            filterValue = filterValue,
            commentsViewModel = commentsViewModel,
            navigateBack = { navigator.pop() }
        )
    }
}