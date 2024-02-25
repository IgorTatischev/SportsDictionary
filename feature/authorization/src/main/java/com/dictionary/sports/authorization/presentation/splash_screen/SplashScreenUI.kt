package com.dictionary.sports.authorization.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.dictionary.sports.ui.components.Background

@Composable
fun SplashScreenUI(
    navigateToWelcomeScreen: () -> Unit,
    navigateToMenuScreen: () -> Unit,
    splashViewModel: SplashViewModel,
) {
    val scale = remember {
        Animatable(0f)
    }

    val animationFinished = remember {
        mutableStateOf(false)
    }

    val loginState = splashViewModel.loginState.collectAsState().value

    LaunchedEffect(key1 = animationFinished.value) {
        if (animationFinished.value) {
            when (loginState) {
                is LogInState.Logged -> navigateToMenuScreen()

                else -> navigateToWelcomeScreen()
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        splashViewModel.isUserLoggedIn()
        scale.animateTo(
            targetValue = .8f,
            animationSpec = tween(
                durationMillis = 1100,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        splashViewModel.isUserLoggedIn()
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1100,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )

        splashViewModel.isUserLoggedIn()
        animationFinished.value = true
    }

    Background(
        addIcon = true,
        scale = scale
    )
}