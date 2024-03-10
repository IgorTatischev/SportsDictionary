package com.dictionary.sports.authorization.presentation.auth_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.R
import com.dictionary.sports.authorization.presentation.auth_screen.AuthorizationScreenModel

@Composable
internal fun LoginDataTextFields(
    authorizationViewModel: AuthorizationScreenModel
) {
    val state = authorizationViewModel.signInState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CustomTextField(
            text = state.loginText,
            labelTextRes = R.string.label_login,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = authorizationViewModel::changeLoginText
        )

        CustomTextField(
            text = state.passwordText,
            labelTextRes = R.string.label_password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true,
            onValueChange = authorizationViewModel::changePasswordText
        )
    }
}