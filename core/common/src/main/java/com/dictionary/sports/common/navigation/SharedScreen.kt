package com.dictionary.sports.common.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import com.dictionary.sports.common.sports.Sports

sealed class SharedScreen : ScreenProvider {
    object Splash : SharedScreen()

    object Menu : SharedScreen()

    object Auth : SharedScreen()

    object Settings : SharedScreen()

    data class Sport(val item: Sports) : SharedScreen()

    data class Comments(val filterValue: Int) : SharedScreen()
}