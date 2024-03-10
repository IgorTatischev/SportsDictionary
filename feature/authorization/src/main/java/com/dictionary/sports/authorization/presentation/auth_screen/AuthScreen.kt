package com.dictionary.sports.authorization.presentation.auth_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.common.navigation.SharedScreen

internal class AuthScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = getScreenModel<AuthorizationScreenModel>()
        val navigator = LocalNavigator.currentOrThrow
        val menuScreen = rememberScreen(SharedScreen.Menu)

        AuthScreenContent(
            screenModel = screenModel,
            navigateToMenuScreen = { navigator.replaceAll(menuScreen) },
            navigateBack =  { navigator.pop() }
        )
    }
}