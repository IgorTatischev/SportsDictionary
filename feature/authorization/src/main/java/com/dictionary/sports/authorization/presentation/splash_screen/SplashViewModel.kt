package com.dictionary.sports.authorization.presentation.splash_screen

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.locale.AppLanguage
import com.dictionary.sports.common.supabase.repository.SupabaseRepository
import com.dictionary.sports.common.supabase.state.LoggedInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale

class SplashViewModel(
    private val dataStorePreferencesRepository: DataStorePreferencesRepository,
    private val supabaseRepository: SupabaseRepository
) : ScreenModel {
    private val token = mutableStateOf("")

    private val _loginState = MutableStateFlow<LogInState>(LogInState.NotLogged)
    val loginState: StateFlow<LogInState> = _loginState

    init {
        setLanguage()
        getToken()
    }

    fun isUserLoggedIn() {
        screenModelScope.launch(Dispatchers.IO) {
            val result = supabaseRepository.isUserLoggedIn(token.value)
            when {
                result is LoggedInState.Success -> if (result.isLoggedIn) _loginState.value =
                    LogInState.Logged
                else _loginState.value = LogInState.NotLogged
            }
        }
    }

    private fun getToken() {
        dataStorePreferencesRepository.getToken().onEach {
            token.value = it
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