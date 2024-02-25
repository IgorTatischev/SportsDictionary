package com.dictionary.sports.common.supabase.state

sealed class AuthResult {
    data class Success(val successMessageRes: Int) : AuthResult()
    data class Error(val errorMessage: String) : AuthResult()
}