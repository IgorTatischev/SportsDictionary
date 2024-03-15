package com.dictionary.sports.authorization.presentation.splash_screen

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.locale.AppLanguage
import com.dictionary.sports.supabase.repository.SupabaseRepository
import com.dictionary.sports.supabase.state.LoggedInStatus
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale

internal class SplashScreenModel(
    private val dataStorePreferencesRepository: DataStorePreferencesRepository,
    private val supabaseRepository: SupabaseRepository,
) : ScreenModel {

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        setLanguage()
        isUserLoggedIn()
    }

    fun isUserLoggedIn() {
        screenModelScope.launch {
            supabaseRepository.isUserLoggedIn.collectLatest {
                when (it) {
                    is LoggedInStatus.Error -> _uiEffect.emit(UiEffect.MoveToAuth)
                    is LoggedInStatus.Success ->
                        if (it.isLoggedIn)
                            _uiEffect.emit(UiEffect.MoveToMenu)
                        else
                            _uiEffect.emit(UiEffect.MoveToAuth)
                }
            }
        }
    }

    private fun setLanguage() {
        screenModelScope.launch {
            dataStorePreferencesRepository.getLanguage().collectLatest { name ->
                val language = AppLanguage.valueOf(name)
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(Locale(language.code)))
            }
        }
    }
}