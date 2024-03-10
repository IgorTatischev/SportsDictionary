package com.dictionary.sports.settings.presentation.screens.settings_screen

import androidx.annotation.StringRes
import com.dictionary.sports.common.locale.AppLanguage

internal data class SettingsScreenState(
    val nameText: String = "",
    val loginText: String = "",
    val passwordText: String = "",
    val selectedLanguages: String = AppLanguage.EN.name,
    val showDialog: Boolean = false
)

internal sealed class UiEffect {
    data class ShowSnackbar(@StringRes val resId: Int) : UiEffect()
}


