package com.dictionary.sports.authorization.presentation.auth_screen.viewmodel

import androidx.annotation.StringRes
import com.dictionary.sports.authorization.R

data class AuthScreenState(
    val loginText: String = "",
    val passwordText: String = ""
)

sealed class UiEffect {
    object NavigateToMenuScreen: UiEffect()
    data class ShowSnackbar(@StringRes val resId: Int): UiEffect()
}