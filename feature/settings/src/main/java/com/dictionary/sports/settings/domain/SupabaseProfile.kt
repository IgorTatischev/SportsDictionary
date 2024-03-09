package com.dictionary.sports.settings.domain

import com.dictionary.sports.settings.domain.model.Profile

interface SupabaseProfile {

    suspend fun deleteUser()

    suspend fun changeUserData(
        login: String,
        userPassword: String,
        name: String,
    ): Result<Unit>

    suspend fun getUserData() : Result<Profile>

    suspend fun signOut()
}