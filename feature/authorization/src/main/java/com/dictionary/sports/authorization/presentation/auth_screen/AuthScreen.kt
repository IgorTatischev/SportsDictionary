package com.dictionary.sports.authorization.presentation.auth_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.authorization.presentation.auth_screen.viewmodel.AuthorizationViewModel
import com.dictionary.sports.common.navigation.SharedScreen

class AuthScreen : Screen {
    @Composable
    override fun Content() {
        val authorizationViewModel = getScreenModel<AuthorizationViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        val menuScreen = rememberScreen(SharedScreen.Menu)

        val navigateBack = { navigator.pop() }
        val navigateToMenuScreen = { navigator.replaceAll(menuScreen) }

        AuthScreenContent(
            authorizationViewModel = authorizationViewModel,
            navigateToMenuScreen = navigateToMenuScreen,
            navigateBack = navigateBack
        )
    }
}