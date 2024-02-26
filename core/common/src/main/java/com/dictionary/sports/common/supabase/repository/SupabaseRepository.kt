package com.dictionary.sports.common.supabase.repository

import com.dictionary.sports.common.supabase.state.AuthResult
import com.dictionary.sports.common.supabase.state.ChangeUserDataState
import com.dictionary.sports.common.supabase.state.LoggedInState

interface SupabaseRepository {

    suspend fun signUp(
        navigateToScreen: () -> Unit,
        userLogin: String,
        userPassword: String
    ): AuthResult

    suspend fun login(
        navigateToScreen: () -> Unit,
        userLogin: String,
        userPassword: String
    ): AuthResult

    suspend fun saveToken()

    //suspend fun deleteUser(): ChangeUserDataState

    suspend fun getCurrentUserName(): String

    suspend fun saveNewNameForUser(newName: String): ChangeUserDataState

    suspend fun isUserLoggedIn(token: String): LoggedInState
}