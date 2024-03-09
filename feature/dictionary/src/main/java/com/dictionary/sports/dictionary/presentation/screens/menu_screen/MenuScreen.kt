package com.dictionary.sports.dictionary.presentation.screens.menu_screen

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
import com.dictionary.sports.common.sports.Sports
import com.dictionary.sports.dictionary.presentation.screens.sport_screen.SportDescriptionScreen

class MenuScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val settingsScreen = rememberScreen(SharedScreen.Settings)

        val navigateToSettingsScreen = { navigator.push(settingsScreen) }
        val navigateToSportScreen: (Sports) -> Unit =
            { navigator.push(SportDescriptionScreen(item = it)) }

        val context = LocalContext.current
        LaunchedEffect(true){
            val activity = context as Activity
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        MenuScreenContent(
            navigateToSettings = navigateToSettingsScreen,
            navigateToSportScreen = navigateToSportScreen,
        )
    }
}