package com.dictionary.sports.settings.di

import cafe.adriel.voyager.core.registry.screenModule
import com.dictionary.sports.common.navigation.SharedScreen
import com.dictionary.sports.settings.presentation.screens.settings_screen.SettingsScreen
import com.dictionary.sports.settings.presentation.screens.settings_screen.SettingsViewModel
import com.dictionary.sports.settings.domain.SupabaseProfile
import com.dictionary.sports.settings.data.SupabaseProfileImpl
import com.dictionary.sports.settings.util.ActionService
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object SettingsModule {

    operator fun invoke() = module {
        factoryOf(::SettingsViewModel)
        singleOf(::ActionService)
        singleOf(::SupabaseProfileImpl) bind SupabaseProfile::class
    }
}

val settingsScreenModule = screenModule {
    register<SharedScreen.Settings> {
        SettingsScreen()
    }
}