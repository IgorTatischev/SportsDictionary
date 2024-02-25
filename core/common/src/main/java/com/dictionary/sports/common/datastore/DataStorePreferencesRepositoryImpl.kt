package com.dictionary.sports.common.datastore

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalSettingsApi::class)
internal class DataStorePreferencesRepositoryImpl(
    private val settings: FlowSettings,
) : DataStorePreferencesRepository {
    override suspend fun saveToken(token: String) {
        settings.putString(TOKEN, token)
    }

    override fun getToken(): Flow<String> = settings.getStringFlow(TOKEN, "")

    override fun getLanguage(): Flow<String> = settings.getStringFlow(LANGUAGE, "EN")

    override suspend fun changeLanguage(language: String) {
        settings.putString(LANGUAGE, language)
    }

    companion object {
        private const val TOKEN = "token"
        private const val LANGUAGE = "language"
    }
}