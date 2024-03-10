package com.dictionary.sports.authorization.di

import cafe.adriel.voyager.core.registry.screenModule
import com.dictionary.sports.authorization.presentation.auth_screen.AuthorizationScreenModel
import com.dictionary.sports.authorization.presentation.splash_screen.SplashScreen
import com.dictionary.sports.authorization.presentation.splash_screen.SplashScreenModel
import com.dictionary.sports.authorization.presentation.welcome_screen.WelcomeScreen
import com.dictionary.sports.authorization.repository.SupabaseAuth
import com.dictionary.sports.authorization.repository.SupabaseAuthImpl
import com.dictionary.sports.common.navigation.SharedScreen
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object AuthorizationModule {
    operator fun invoke() =  module {
        factoryOf(::AuthorizationScreenModel)
        factoryOf(::SplashScreenModel)
        singleOf(::SupabaseAuthImpl) bind SupabaseAuth::class
    }
}

val authScreenModule = screenModule {

    register<SharedScreen.Splash> {
        SplashScreen()
    }

    register<SharedScreen.Auth> {
        WelcomeScreen()
    }
}