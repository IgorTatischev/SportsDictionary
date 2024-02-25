package com.dictionary.sports.settings.presentation.screens.settings_screen

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.locale.AppLanguage
import com.dictionary.sports.common.supabase.repository.SupabaseRepository
import com.dictionary.sports.common.supabase.state.ChangeUserDataState
import com.dictionary.sports.settings.R
import com.dictionary.sports.settings.repository.ActionRepository
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

class SettingsViewModel(
    private val dataStorePreferencesRepository: DataStorePreferencesRepository,
    private val actionRepository: ActionRepository,
    private val supabaseRepository: SupabaseRepository
) : ScreenModel {

    private val _state = MutableStateFlow(SettingsScreenState())
    val state = _state.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        getLanguage()
    }

    fun setShowDialogTrue() {
        getCurrentUserName()
        _showDialog.value = true
    }

    fun setShowDialogFalse() {
        _showDialog.value = false
    }

    fun openEmail() {
        screenModelScope.launch {
            try {
                actionRepository.openEmail()
            } catch (exception: Exception) {
                _uiEffect.emit(UiEffect.ShowSnackbar(CoreRes.string.error))
            }
        }
    }

    fun openMarket() {
        screenModelScope.launch {
            try {
                actionRepository.openMarket()
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

    fun deleteUser() {
        screenModelScope.launch(Dispatchers.IO) {
            val result = supabaseRepository.deleteUser()

            when (result) {
                ChangeUserDataState.Success -> _uiEffect.emit(UiEffect.ShowSnackbar(R.string.success_delete_account))

                ChangeUserDataState.Error -> _uiEffect.emit(UiEffect.ShowSnackbar(CoreRes.string.error))
            }
        }
    }

    private fun getCurrentUserName() {
        screenModelScope.launch(Dispatchers.IO) {
            _userName.value = supabaseRepository.getCurrentUserName()
        }
    }

    fun changeUserName(newValue: String) {
        _userName.value = newValue
    }

    fun saveNewNameForUser(newName: String) {
        screenModelScope.launch(Dispatchers.IO) {
            when (supabaseRepository.saveNewNameForUser(newName = newName)) {
                ChangeUserDataState.Success -> _uiEffect.emit(UiEffect.ShowSnackbar(R.string.success_name_change))
                ChangeUserDataState.Error -> _userName.value = ""
            }
        }
    }
}