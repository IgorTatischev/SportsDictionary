package com.dictionary.sports.authorization.presentation.auth_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dictionary.sports.authorization.R

@Composable
fun CustomTextField(
    text: String,
    labelTextRes: Int,
    keyboardOptions: KeyboardOptions,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    val isPasswordVisible = remember {
        mutableStateOf(false)
    }

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        keyboardOptions = keyboardOptions,
        visualTransformation = if (!isPasswordVisible.value && isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Bottom)
            ) {
                Text(
                    text = stringResource(id = labelTextRes),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    innerTextField()

                    if (isPassword)
                        Text(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    isPasswordVisible.value = !isPasswordVisible.value
                                },
                            text = if (!isPasswordVisible.value) stringResource(id = R.string.label_show)
                            else stringResource(id = R.string.label_hide),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 12.sp
                        )
                }

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    )
}