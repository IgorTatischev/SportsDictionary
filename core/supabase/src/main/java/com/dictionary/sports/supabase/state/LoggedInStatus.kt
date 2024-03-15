package com.dictionary.sports.supabase.state

sealed class LoggedInStatus {
    data class Error(val message: String): LoggedInStatus()

    data class Success(val isLoggedIn: Boolean): LoggedInStatus()
}