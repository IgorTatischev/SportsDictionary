package com.dictionary.sports.supabase.state

sealed class LoggedInState {
    data class Error(val message: String): LoggedInState()

    data class Success(val isLoggedIn: Boolean): LoggedInState()
}