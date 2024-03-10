package com.dictionary.sports.authorization.presentation.auth_screen

import androidx.annotation.StringRes

internal data class AuthScreenState(
    val loginText: String = "",
    val passwordText: String = ""
)

internal sealed class UiEffect {
    object NavigateToMenuScreen: UiEffect()
    data class ShowSnackbar(@StringRes val resId: Int): UiEffect()
}