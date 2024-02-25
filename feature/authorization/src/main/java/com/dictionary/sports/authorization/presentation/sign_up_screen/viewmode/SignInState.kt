package com.dictionary.sports.authorization.presentation.sign_up_screen.viewmode

import androidx.annotation.StringRes
import com.dictionary.sports.authorization.R

data class SignInState(
    @StringRes val currentUserSuccess: Int = R.string.empty,
    val currentUserError: String = "",
    @StringRes val googleAuthError: Int = R.string.empty,
    val loginText: String = "",
    val passwordText: String = ""
)

sealed class UiEffect {
    object NavigateToMenuScreen: UiEffect()
}