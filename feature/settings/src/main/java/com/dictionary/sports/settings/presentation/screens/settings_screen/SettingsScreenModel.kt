package com.dictionary.sports.settings.presentation.screens.settings_screen

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.locale.AppLanguage
import com.dictionary.sports.settings.domain.SupabaseProfile
import com.dictionary.sports.settings.util.ActionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import com.dictionary.sports.resources.R as CoreRes

internal class SettingsScreenModel(
    private val dataStorePreferencesRepository: DataStorePreferencesRepository,
    private val actionService: ActionService,
    private val supabaseProfile: SupabaseProfile,
) : ScreenModel {

    private val _state = MutableStateFlow(SettingsScreenState())
    val state = _state.asStateFlow()

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        getLanguage()
    }

    fun showDialog() {
        getProfile()
    }

    fun dialogDismiss() {
        _state.update { it.copy(showDialog = false) }
    }

    fun openEmail() {
        screenModelScope.launch {
            try {
                actionService.openEmail()
            } catch (exception: Exception) {
                _uiEffect.emit(UiEffect.ShowSnackbar(CoreRes.string.error))
            }
        }
    }

    fun openMarket() {
        screenModelScope.launch {
            try {
                actionService.openMarket()
            } catch (exception: Exception) {
                _uiEffect.emit(UiEffect.ShowSnackbar(CoreRes.string.error))
            }
        }
    }

    fun changeLanguage(language: AppLanguage) {
        screenModelScope.launch {
            _state.update { it.copy(selectedLanguages = language.name) }
            dataStorePreferencesRepository.changeLanguage(language.name)
        }
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(Locale(language.code)))
    }

    private fun getLanguage() {
        screenModelScope.launch {
            dataStorePreferencesRepository.getLanguage().collectLatest { name ->
                _state.update { it.copy(selectedLanguages = name) }
            }
        }
    }

    fun setLogin(newValue: String) = _state.update { it.copy(loginText = newValue) }
    fun setPassword(newValue: String) = _state.update { it.copy(passwordText = newValue) }
    fun setUserName(newValue: String) = _state.update { it.copy(nameText = newValue) }

    private fun getProfile() {
        screenModelScope.launch(Dispatchers.IO) {
            supabaseProfile.getUserData()
                .onSuccess { profile ->
                    _state.update {
                        it.copy(
                            loginText = profile.email,
                            nameText = profile.name,
                            passwordText = "",
                            showDialog = true
                        )
                    }
                }
                .onFailure {
                    _state.update { it.copy(showDialog = false) }
                    _uiEffect.emit(UiEffect.ShowSnackbar(CoreRes.string.error))
                }
        }
    }

    fun changeData() = with(_state.value) {
        screenModelScope.launch(Dispatchers.IO) {
            supabaseProfile.changeUserData(
                login = loginText,
                userPassword = passwordText,
                name = nameText,
            ).onFailure {
                _state.update { it.copy(showDialog = false) }
                _uiEffect.emit(UiEffect.ShowSnackbar(CoreRes.string.error))
            }
        }
    }

    fun deleteUser() {
        screenModelScope.launch(Dispatchers.IO) {
            supabaseProfile.deleteUser()
        }
    }

    fun signOut() = screenModelScope.launch { supabaseProfile.signOut() }
}