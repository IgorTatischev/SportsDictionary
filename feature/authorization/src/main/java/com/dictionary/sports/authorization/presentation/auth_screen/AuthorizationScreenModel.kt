package com.dictionary.sports.authorization.presentation.auth_screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.authorization.R
import com.dictionary.sports.authorization.domain.use_case.SignInUseCase
import com.dictionary.sports.authorization.domain.use_case.SignUpUseCase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class AuthorizationScreenModel(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    client: SupabaseClient,
) : ScreenModel {

    private val _signInState = MutableStateFlow(AuthScreenState())
    val signInState = _signInState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    val googleClient = client

    fun changeLoginText(newValue: String) {
        _signInState.update { it.copy(loginText = newValue) }
    }

    fun changePasswordText(newValue: String) {
        _signInState.update { it.copy(passwordText = newValue) }
    }

    fun signUp() = with(_signInState.value) {
        screenModelScope.launch(Dispatchers.IO) {
            signUpUseCase(
                login = loginText,
                userPassword = passwordText,
            ).onFailure {
                _uiEffect.emit(UiEffect.ShowSnackbar(R.string.login_error))
            }.onSuccess {
                _uiEffect.emit(UiEffect.NavigateToMenuScreen)
            }
        }
    }

    fun signIn() = with(_signInState.value) {
        screenModelScope.launch(Dispatchers.IO) {
            signInUseCase(
                login = loginText,
                userPassword = passwordText
            ).onFailure {
                _uiEffect.emit(UiEffect.ShowSnackbar(R.string.login_error))
            }.onSuccess {
                _uiEffect.emit(UiEffect.NavigateToMenuScreen)
            }
        }
    }

    fun loginWithGoogle(result: NativeSignInResult) {
        screenModelScope.launch(Dispatchers.IO) {
            when (result) {
                is NativeSignInResult.Success ->
                    _uiEffect.emit(UiEffect.NavigateToMenuScreen)

                is NativeSignInResult.Error ->
                    _uiEffect.emit(UiEffect.ShowSnackbar(R.string.login_error))

                is NativeSignInResult.NetworkError ->
                    _uiEffect.emit(UiEffect.ShowSnackbar(R.string.error))

                is NativeSignInResult.ClosedByUser -> {}
            }
        }
    }
}