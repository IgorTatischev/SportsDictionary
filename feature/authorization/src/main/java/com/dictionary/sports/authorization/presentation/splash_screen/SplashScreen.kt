package com.dictionary.sports.authorization.presentation.splash_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.authorization.presentation.welcome_screen.WelcomeScreen
import com.dictionary.sports.common.navigation.SharedScreen

internal class SplashScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = getScreenModel<SplashScreenModel>()

        val navigator = LocalNavigator.currentOrThrow
        val menuScreen = rememberScreen(SharedScreen.Menu)

        SplashScreenContent(
            navigateToWelcomeScreen = { navigator.replaceAll(WelcomeScreen()) },
            navigateToMenuScreen = { navigator.replaceAll(menuScreen) },
            screenModel = screenModel
        )
    }
}