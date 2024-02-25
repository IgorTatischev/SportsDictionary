package com.dictionary.sports.authorization.presentation.splash_screen

sealed class LogInState {

    object Logged : LogInState()

    object NotLogged : LogInState()
}