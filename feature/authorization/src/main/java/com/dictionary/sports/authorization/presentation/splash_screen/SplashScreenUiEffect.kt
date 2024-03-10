package com.dictionary.sports.authorization.presentation.splash_screen

internal sealed class UiEffect {
    object MoveToMenu: UiEffect()
    object MoveToAuth: UiEffect()
}