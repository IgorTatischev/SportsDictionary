package com.dictionary.sports.authorization.presentation.welcome_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.authorization.presentation.auth_screen.AuthScreen
import com.dictionary.sports.common.navigation.SharedScreen

class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val menuScreen = rememberScreen(SharedScreen.Menu)
        val navigateToSignUpScreen = { navigator.push(AuthScreen()) }
        val navigateToMenuScreen = { navigator.push(menuScreen) }

        WelcomeScreenContent(
            navigateToSignUpScreen = navigateToSignUpScreen,
            navigateToMenuScreen = navigateToMenuScreen
        )
    }
}