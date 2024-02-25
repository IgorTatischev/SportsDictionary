package com.dictionary.sports.settings.di

import cafe.adriel.voyager.core.registry.screenModule
import com.dictionary.sports.common.navigation.SharedScreen
import com.dictionary.sports.settings.presentation.screens.settings_screen.SettingsScreen
import com.dictionary.sports.settings.presentation.screens.settings_screen.SettingsViewModel
import com.dictionary.sports.settings.repository.ActionRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object SettingsModule {

    operator fun invoke() = module {
        factoryOf(::SettingsViewModel)
        singleOf(::ActionRepository)
    }
}

val settingsScreenModule = screenModule {
    register<SharedScreen.Settings> {
        SettingsScreen()
    }
}