package com.dictionary.sports.authorization.domain.repository

internal interface SupabaseAuth {
    suspend fun signUp(
        userLogin: String,
        userPassword: String,
    ): Result<Unit>

    suspend fun signIn(
        userLogin: String,
        userPassword: String
    ): Result<Unit>

}