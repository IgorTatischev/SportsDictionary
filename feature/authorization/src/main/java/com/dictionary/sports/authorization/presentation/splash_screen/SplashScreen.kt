package com.dictionary.sports.authorization.presentation.splash_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.authorization.presentation.welcome_screen.WelcomeScreen
import com.dictionary.sports.common.navigation.SharedScreen

class SplashScreen : Screen {
    @Composable
    override fun Content() {

        val splashViewModel = getScreenModel<SplashViewModel>()

        val navigator = LocalNavigator.currentOrThrow
        val menuScreen = rememberScreen(SharedScreen.Menu)

        val navigateToWelcomeScreen = { navigator.replaceAll(WelcomeScreen()) }
        val navigateToMenuScreen = { navigator.replaceAll(menuScreen) }

        SplashScreenContent(
            navigateToWelcomeScreen = navigateToWelcomeScreen,
            navigateToMenuScreen = navigateToMenuScreen,
            splashViewModel = splashViewModel
        )
    }
}