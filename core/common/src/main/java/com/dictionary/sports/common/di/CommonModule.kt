package com.dictionary.sports.common.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.datastore.DataStorePreferencesRepositoryImpl
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module

object CommonModule {
    @OptIn(ExperimentalSettingsApi::class)
    operator fun invoke() = module {
        single { createSettings() }
        singleOf(::DataStorePreferencesRepositoryImpl) bind DataStorePreferencesRepository::class
    }
}

private const val PREFERENCES_DATA_STORE_NAME = "preferences_data_store"

private val Context.dataStore by preferencesDataStore(PREFERENCES_DATA_STORE_NAME)

@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
internal fun Scope.createSettings(): FlowSettings =
    DataStoreSettings(androidApplication().dataStore)