package com.dictionary.sports.common.datastore

import kotlinx.coroutines.flow.Flow

interface DataStorePreferencesRepository {
    suspend fun saveToken(token: String)

    fun getToken(): Flow<String>

    fun getLanguage(): Flow<String>

    suspend fun changeLanguage(language: String)
}