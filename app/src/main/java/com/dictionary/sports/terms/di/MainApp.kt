package com.dictionary.sports.terms.di

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.dictionary.sports.authorization.di.authScreenModule
import com.dictionary.sports.common.di.CommonModule
import com.dictionary.sports.dictionary.di.DictionaryModule
import com.dictionary.sports.dictionary.di.dictionaryScreenModule
import com.dictionary.sports.settings.di.SettingsModule
import com.dictionary.sports.settings.di.settingsScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            dictionaryScreenModule()
            authScreenModule()
            settingsScreenModule()
        }

        startKoin {
            androidContext(this@MainApp)
            androidLogger()
            modules(
                listOf(
                    com.dictionary.sports.authorization.di.AuthorizationModule(),
                    DictionaryModule(),
                    SettingsModule(),
                    CommonModule(),
                )
            )
        }
    }
}