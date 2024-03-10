package com.dictionary.sports.settings.presentation.screens.settings_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.common.navigation.SharedScreen

internal class SettingsScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel = getScreenModel<SettingsScreenModel>()
        val navigator = LocalNavigator.currentOrThrow
        val authScreen = rememberScreen(SharedScreen.Auth)

        SettingsScreenContent(
            screenModel = screenModel,
            navigateBack = { navigator.pop() },
            navigateToAuthScreen =  { navigator.replaceAll(authScreen) }
        )
    }
}


