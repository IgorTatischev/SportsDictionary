package com.dictionary.sports.settings.presentation.screens.settings_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dictionary.sports.common.navigation.SharedScreen


class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        val settingsViewModel = getScreenModel<SettingsViewModel>()

        val navigator = LocalNavigator.currentOrThrow
        val registrationScreen = rememberScreen(SharedScreen.Auth)

        val navigateBack = { navigator.pop() }
        val navigateToSignInScreen = { navigator.push(registrationScreen) }

        SettingsScreenUi(
            viewModel = settingsViewModel,
            navigateBack = navigateBack,
            navigateToSignInScreen = navigateToSignInScreen
        )
    }
}


