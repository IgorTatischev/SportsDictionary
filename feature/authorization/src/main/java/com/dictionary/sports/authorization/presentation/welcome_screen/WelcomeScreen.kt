package com.dictionary.sports.authorization.presentation.welcome_screen

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.common.navigation.SharedScreen

class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val registerScreen = rememberScreen(SharedScreen.Register)
        val menuScreen = rememberScreen(SharedScreen.Menu)

        val navigateToRegistrationScreen = { navigator.push(registerScreen) }
        val navigateToMenuScreen = { navigator.push(menuScreen) }

        val context = LocalContext.current
        LaunchedEffect(true){
            val activity = context as Activity
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        WelcomeScreenUI(
            navigateToRegistrationScreen = navigateToRegistrationScreen,
            navigateToMenuScreen = navigateToMenuScreen
        )
    }
}