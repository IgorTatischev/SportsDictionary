package com.dictionary.sports.authorization.presentation.auth_screen.viewmodel

import android.util.Log
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.authorization.R
import com.dictionary.sports.authorization.repository.SupabaseAuth
import com.dictionary.sports.supabase.repository.SupabaseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val client: SupabaseClient,
    private val auth: SupabaseAuth,
    private val supabaseRepository: SupabaseRepository
) : ScreenModel {
    //TODO GOOGLE AUTH
    private val _signInState = MutableStateFlow(AuthScreenState())
    val signInState = _signInState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    //val supabaseClientFromVM = client

    fun changeLoginText(newValue: String) {
        _signInState.update { it.copy(loginText = newValue) }
    }

    fun changePasswordText(newValue: String) {
        _signInState.update { it.copy(passwordText = newValue) }
    }

    fun signUp(navigateToScreen: () -> Unit) = with(_signInState.value) {
        screenModelScope.launch(Dispatchers.IO) {
            auth.signUp(
                navigateToScreen = navigateToScreen,
                userLogin = loginText,
                userPassword = passwordText
            ).onFailure {
                _uiEffect.emit(UiEffect.ShowSnackbar(R.string.login_error))
            }
        }
    }

    fun signIn(navigateToScreen: () -> Unit) = with(_signInState.value) {
        screenModelScope.launch(Dispatchers.IO) {
            auth.signIn(
                navigateToScreen = navigateToScreen,
                userLogin = loginText,
                userPassword = passwordText
            ).onFailure {
                _uiEffect.emit(UiEffect.ShowSnackbar(R.string.login_error))
            }
        }
    }

    fun loginWithGoogle(result: NativeSignInResult) {
        when (result) {
            is NativeSignInResult.Success -> {
                screenModelScope.launch(Dispatchers.IO) {
                     supabaseRepository.saveToken()
                    _uiEffect.emit(UiEffect.NavigateToMenuScreen)
                }
            }

            is NativeSignInResult.Error -> {}
                //_signInState.update { it.copy(googleAuthError = R.string.error) }

            is NativeSignInResult.NetworkError -> {}
                //_signInState.update { it.copy(googleAuthError = R.string.network_error) }

            is NativeSignInResult.ClosedByUser -> {}
        }
    }
}