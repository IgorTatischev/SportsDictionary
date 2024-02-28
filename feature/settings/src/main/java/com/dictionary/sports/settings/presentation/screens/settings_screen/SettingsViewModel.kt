package com.dictionary.sports.settings.presentation.screens.settings_screen

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.locale.AppLanguage
import com.dictionary.sports.settings.repository.SupabaseProfile
import com.dictionary.sports.settings.util.ActionService
import com.dictionary.sports.supabase.repository.SupabaseRepository
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
    private val actionService: ActionService,
    private val supabaseRepository: SupabaseRepository,
    private val supabaseProfile: SupabaseProfile
) : ScreenModel {

    private val _state = MutableStateFlow(SettingsScreenState())
    val state = _state.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

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

    //todo delete
//    fun deleteUser() {
//        screenModelScope.launch(Dispatchers.IO) {
//            val result = supabaseRepository.deleteUser()
//
//            when (result) {
//                ChangeUserDataState.Success -> _uiEffect.emit(UiEffect.ShowSnackbar(R.string.success_delete_account))
//
//                ChangeUserDataState.Error -> _uiEffect.emit(UiEffect.ShowSnackbar(CoreRes.string.error))
//            }
//        }
//    }

    private fun getCurrentUserName() {
        screenModelScope.launch(Dispatchers.IO) {
            setUserName(supabaseRepository.getCurrentUserName())
        }
    }

    fun setUserName(newValue: String) = _state.update { it.copy(userName = newValue) }


    //todo change name
//    fun saveNewNameForUser(newName: String) {
//        screenModelScope.launch(Dispatchers.IO) {
//            when (supabaseRepository.saveNewNameForUser(newName = newName)) {
//                ChangeUserDataState.Success -> _uiEffect.emit(UiEffect.ShowSnackbar(R.string.success_name_change))
//                ChangeUserDataState.Error -> _userName.value = ""
//            }
//        }
//    }
}