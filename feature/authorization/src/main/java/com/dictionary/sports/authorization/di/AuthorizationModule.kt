package com.dictionary.sports.authorization.di

import cafe.adriel.voyager.core.registry.screenModule
import com.dictionary.sports.authorization.data.repository.SupabaseAuthImpl
import com.dictionary.sports.authorization.domain.repository.SupabaseAuth
import com.dictionary.sports.authorization.domain.use_case.SaveTokenUseCase
import com.dictionary.sports.authorization.domain.use_case.SignInUseCase
import com.dictionary.sports.authorization.domain.use_case.SignUpUseCase
import com.dictionary.sports.authorization.presentation.auth_screen.AuthorizationScreenModel
import com.dictionary.sports.authorization.presentation.splash_screen.SplashScreen
import com.dictionary.sports.authorization.presentation.splash_screen.SplashScreenModel
import com.dictionary.sports.authorization.presentation.welcome_screen.WelcomeScreen
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
        singleOf(::SignInUseCase)
        singleOf(::SignUpUseCase)
        singleOf(::SaveTokenUseCase)
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