package com.dictionary.sports.supabase.state

sealed class LoggedInState {
    object Error: LoggedInState()

    data class Success(val isLoggedIn: Boolean): LoggedInState()
}