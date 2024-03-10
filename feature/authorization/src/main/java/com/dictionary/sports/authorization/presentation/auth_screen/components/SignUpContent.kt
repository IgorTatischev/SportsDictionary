package com.dictionary.sports.authorization.presentation.auth_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.R
import com.dictionary.sports.authorization.presentation.auth_screen.AuthorizationScreenModel

@Composable
internal fun SignUpContent(
    navigateToMenuScreen: () -> Unit,
    authorizationViewModel: AuthorizationScreenModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginDataTextFields(authorizationViewModel = authorizationViewModel)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AuthButton {
                authorizationViewModel.signIn(
                    navigateToScreen = navigateToMenuScreen
                )
            }

            AuthButton(textRes = R.string.button_sign_up) {
                authorizationViewModel.signUp(
                    navigateToScreen = navigateToMenuScreen
                )
            }
        }

        GoogleSignInDisplay(screenModel = authorizationViewModel)
    }
}