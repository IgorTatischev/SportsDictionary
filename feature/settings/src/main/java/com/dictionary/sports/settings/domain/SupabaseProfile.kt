package com.dictionary.sports.settings.domain

import com.dictionary.sports.settings.domain.model.Profile

internal interface SupabaseProfile {

    suspend fun deleteUser(): Result<Unit>

    suspend fun changeUserData(
        login: String,
        userPassword: String,
        name: String,
    ): Result<Unit>

    suspend fun getUserData() : Result<Profile>

    suspend fun signOut(): Result<Unit>
}