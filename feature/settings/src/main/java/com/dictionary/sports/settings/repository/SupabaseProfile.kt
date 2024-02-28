package com.dictionary.sports.settings.repository

interface SupabaseProfile {

    suspend fun deleteUser(): Result<Unit>

    suspend fun changeProfileName(name: String): Result<Unit>

}