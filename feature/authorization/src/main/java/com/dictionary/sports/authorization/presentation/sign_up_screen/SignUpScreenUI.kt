package com.dictionary.sports.authorization.presentation.sign_up_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.presentation.sign_up_screen.components.Header
import com.dictionary.sports.authorization.presentation.sign_up_screen.components.SignUpContent
import com.dictionary.sports.authorization.presentation.sign_up_screen.viewmode.AuthorizationViewModel
import com.dictionary.sports.ui.components.Background

@Composable
fun SignUpScreenUI(
    navigateToMenuScreen: () -> Unit,
    navigateBack: () -> Boolean,
    authorizationViewModel: AuthorizationViewModel
) {
    Background(alpha = .6f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Header(navigateBack = navigateBack)

        SignUpContent(
            navigateToMenuScreen = navigateToMenuScreen,
            authorizationViewModel = authorizationViewModel
        )

        Spacer(modifier = Modifier.padding(bottom = 26.dp))
    }
}