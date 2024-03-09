package com.dictionary.sports.authorization.presentation.welcome_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.presentation.welcome_screen.components.BottomButtons
import com.dictionary.sports.authorization.presentation.welcome_screen.components.Header
import com.dictionary.sports.ui.components.Background

@Composable
fun WelcomeScreenContent(
    navigateToSignUpScreen: () -> Unit,
    navigateToMenuScreen: () -> Unit
) {
    Background(
        alpha = .6f,
        addIcon = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 40.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Header()

        BottomButtons(
            navigateToRegistrationScreen = navigateToSignUpScreen,
            navigateToMenuScreen = navigateToMenuScreen
        )
    }
}