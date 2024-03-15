package com.dictionary.sports.supabase.repository

import com.dictionary.sports.supabase.state.LoggedInStatus
import kotlinx.coroutines.flow.Flow

interface SupabaseRepository {

    val isUserLoggedIn: Flow<LoggedInStatus>
    suspend fun getToken(): String?
    suspend fun getCurrentUserName(): String

}