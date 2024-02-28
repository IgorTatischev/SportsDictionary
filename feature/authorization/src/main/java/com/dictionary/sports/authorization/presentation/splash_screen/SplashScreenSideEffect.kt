package com.dictionary.sports.authorization.presentation.splash_screen

sealed class SplashScreenSideEffect {
    object MoveToMenu: SplashScreenSideEffect()
    object MoveToAuth: SplashScreenSideEffect()
}