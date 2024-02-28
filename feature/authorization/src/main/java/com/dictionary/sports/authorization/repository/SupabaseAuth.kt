package com.dictionary.sports.authorization.repository

interface SupabaseAuth {

    suspend fun signUp(
        navigateToScreen: () -> Unit,
        userLogin: String,
        userPassword: String,
    ): Result<Unit>

    suspend fun signIn(
        navigateToScreen: () -> Unit,
        userLogin: String,
        userPassword: String
    ): Result<Unit>

}