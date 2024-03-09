package com.dictionary.sports.authorization.presentation.splash_screen

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.locale.AppLanguage
import com.dictionary.sports.supabase.repository.SupabaseRepository
import com.dictionary.sports.supabase.state.LoggedInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale

class SplashViewModel(
    private val dataStorePreferencesRepository: DataStorePreferencesRepository,
    private val supabaseRepository: SupabaseRepository
) : ScreenModel {

    private var token = ""

    private val _sideEffect = MutableSharedFlow<SplashScreenSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        setLanguage()
        getToken()
    }

     fun isUserLoggedIn() {
        screenModelScope.launch(Dispatchers.IO) {
            when (val result = supabaseRepository.isUserLoggedIn(token)) {
                is LoggedInState.Error -> {
                    _sideEffect.emit(SplashScreenSideEffect.MoveToAuth)
                }
                is LoggedInState.Success -> {
                    if(result.isLoggedIn)
                        _sideEffect.emit(SplashScreenSideEffect.MoveToMenu)
                    else
                        _sideEffect.emit(SplashScreenSideEffect.MoveToAuth)
                }
            }
        }
    }

    private fun getToken() {
        dataStorePreferencesRepository.getToken().onEach {
            token = it
        }.launchIn(screenModelScope)
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