package com.dictionary.sports.terms

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.dictionary.sports.common.navigation.SharedScreen
import com.dictionary.sports.ui.theme.DictionaryRushTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryRushTheme {
                val splash = rememberScreen(provider = SharedScreen.Splash)
                Navigator(splash) { navigator ->
                    FadeTransition(navigator)
                }
            }
        }
    }
}