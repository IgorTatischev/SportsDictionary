package com.dictionary.sports.settings.presentation.screens.settings_screen

import androidx.annotation.StringRes
import com.dictionary.sports.common.locale.AppLanguage

data class SettingsScreenState(
    val userName: String = "",
    val selectedLanguages: String = AppLanguage.EN.name
)

sealed class UiEffect {
    data class ShowSnackbar(@StringRes val resId: Int) : UiEffect()
}


