package com.dictionary.sports.authorization.presentation.sign_up_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.R
import com.dictionary.sports.authorization.presentation.sign_up_screen.viewmode.AuthorizationViewModel

@Composable
fun SignUpContent(
    navigateToMenuScreen: () -> Unit,
    authorizationViewModel: AuthorizationViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFields(authorizationViewModel = authorizationViewModel)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SignUpButton(
                action = {
                    authorizationViewModel.signUp(
                        navigateToScreen = navigateToMenuScreen
                    )
                }
            )

            SignUpButton(
                action = {
                    authorizationViewModel.signIn(
                        navigateToScreen = navigateToMenuScreen
                    )
                },
                textRes = R.string.button_log_in
            )
        }

        GoogleSignInDisplay(
            navigateToMenuScreen = navigateToMenuScreen,
            authorizationViewModel = authorizationViewModel
        )
    }
}