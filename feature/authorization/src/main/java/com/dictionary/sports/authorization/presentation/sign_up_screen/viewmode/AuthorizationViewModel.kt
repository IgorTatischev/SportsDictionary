package com.dictionary.sports.authorization.presentation.sign_up_screen.viewmode

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.dictionary.sports.authorization.R
import com.dictionary.sports.common.supabase.SupabaseClient
import com.dictionary.sports.common.supabase.repository.SupabaseRepository
import com.dictionary.sports.common.supabase.state.AuthResult
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val supabaseClient: SupabaseClient,
    private val supabaseRepository: SupabaseRepository,
) : ScreenModel {

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    val supabaseClientFromVM = supabaseClient

    fun changeLoginText(newValue: String) {
        _signInState.update { it.copy(loginText = newValue) }
    }

    fun changePasswordText(newValue: String) {
        _signInState.update { it.copy(passwordText = newValue) }
    }

    fun signUp(
        navigateToScreen: () -> Unit,
    ) = with(_signInState.value) {
        screenModelScope.launch(Dispatchers.IO) {
            val result = supabaseRepository.signUp(
                navigateToScreen = navigateToScreen,
                userLogin = loginText,
                userPassword = passwordText
            )

            _signInState.update {
                when (result) {
                    is AuthResult.Success -> it.copy(currentUserSuccess = result.successMessageRes)
                    is AuthResult.Error -> it.copy(currentUserError = result.errorMessage)
                }
            }
        }
    }

    fun login(
        navigateToScreen: () -> Unit,
    ) = with(_signInState.value) {
        screenModelScope.launch(Dispatchers.IO) {
            val result = supabaseRepository.login(
                navigateToScreen = navigateToScreen,
                userLogin = loginText,
                userPassword = passwordText
            )

            _signInState.update {
                when (result) {
                    is AuthResult.Success -> it.copy(currentUserSuccess = result.successMessageRes)
                    is AuthResult.Error -> it.copy(currentUserError = result.errorMessage)
                }
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

            is NativeSignInResult.Error ->
                _signInState.update { it.copy(googleAuthError = R.string.error) }

            is NativeSignInResult.NetworkError ->
                _signInState.update { it.copy(googleAuthError = R.string.network_error) }

            is NativeSignInResult.ClosedByUser -> {}
        }
    }
}