package com.dictionary.sports.supabase.repository

import com.dictionary.sports.supabase.state.LoggedInState

interface SupabaseRepository {
    suspend fun saveToken()

    suspend fun getCurrentUserName(): String

    suspend fun isUserLoggedIn(token: String): LoggedInState
}