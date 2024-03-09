package com.dictionary.sports.settings.presentation.screens.settings_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dictionary.sports.settings.R
import com.dictionary.sports.settings.presentation.screens.settings_screen.SettingsViewModel

@Composable
fun ChangeNameDialog(
    settingsViewModel: SettingsViewModel
) {
    val state = settingsViewModel.state.collectAsState().value

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        title = {
            Text(
                text = stringResource(id = R.string.change_name),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = state.loginText,
                    onValueChange = {
                        settingsViewModel.setLogin(it)
                    },
                    placeholder = { Text(text = stringResource(id = R.string.login))},
                    shape = RoundedCornerShape(30),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
                OutlinedTextField(
                    value = state.passwordText,
                    onValueChange = {
                        settingsViewModel.setPassword(it)
                    },
                    placeholder = { Text(text = stringResource(R.string.password))},
                    shape = RoundedCornerShape(30),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
                OutlinedTextField(
                    value = state.nameText,
                    onValueChange = {
                        settingsViewModel.setUserName(it)
                    },
                    placeholder = { Text(text = stringResource(id = R.string.name))},
                    shape = RoundedCornerShape(30),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }
        },
        onDismissRequest = {
            settingsViewModel.dialogDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    settingsViewModel.changeData()
                    settingsViewModel.dialogDismiss()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.change_name_confirm),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    settingsViewModel.dialogDismiss()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.change_name_dismiss),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}