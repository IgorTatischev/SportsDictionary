package com.dictionary.sports.authorization.di

import cafe.adriel.voyager.core.registry.screenModule
import com.dictionary.sports.authorization.presentation.sign_up_screen.SignUpScreen
import com.dictionary.sports.authorization.presentation.sign_up_screen.viewmode.AuthorizationViewModel
import com.dictionary.sports.authorization.presentation.splash_screen.SplashScreen
import com.dictionary.sports.authorization.presentation.splash_screen.SplashViewModel
import com.dictionary.sports.authorization.presentation.welcome_screen.WelcomeScreen
import com.dictionary.sports.common.navigation.SharedScreen
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object AuthorizationModule {
    operator fun invoke() =  module {
        factoryOf(::AuthorizationViewModel)
        factoryOf(::SplashViewModel)
    }
}

val authScreenModule = screenModule {

    register<SharedScreen.Splash> {
        SplashScreen()
    }

    register<SharedScreen.Register> {
        SignUpScreen()
    }

    register<SharedScreen.Welcome> {
        WelcomeScreen()
    }
}