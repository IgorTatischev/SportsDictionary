package com.dictionary.sports.dictionary.presentation.screens.sport_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.common.sports.Sports
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.CommentsScreen


data class SportDescriptionScreen(val item: Sports) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val navigateToCommentsScreen: (Int) -> Unit =
            { navigator.push(CommentsScreen(filterValue = it)) }

        SportDescriptionScreenContent(
            item = item,
            navigateBack = { navigator.pop() },
            navigateToComments = navigateToCommentsScreen
        )
    }
}