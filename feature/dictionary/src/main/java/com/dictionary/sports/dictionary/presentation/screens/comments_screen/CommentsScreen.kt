package com.dictionary.sports.dictionary.presentation.screens.comments_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

internal class CommentsScreen(val filterValue: Int) : Screen {
    @Composable
    override fun Content() {

        val screenModel = getScreenModel<CommentsScreenModel>()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            screenModel.isUserLoggedIn()
        }

        CommentsScreenContent(
            filterValue = filterValue,
            screenModel = screenModel,
            navigateBack = { navigator.pop() }
        )

        DisposableEffect(Unit) {
            onDispose { screenModel.leaveComments() }
        }
    }
}