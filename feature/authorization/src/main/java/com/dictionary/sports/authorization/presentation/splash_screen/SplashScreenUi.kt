package com.dictionary.sports.authorization.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.dictionary.sports.ui.components.Background

@Composable
fun SplashScreenUi(
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

    LaunchedEffect(key1 = splashViewModel.sideEffect) {
        if (animationFinished.value) {
            splashViewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    SplashScreenSideEffect.MoveToMenu -> navigateToMenuScreen()
                    SplashScreenSideEffect.MoveToAuth -> navigateToWelcomeScreen()
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        scale.animateTo(
            targetValue = .8f,
            animationSpec = tween(
                durationMillis = 1100,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1100,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        animationFinished.value = true
    }

    Background(
        addIcon = true,
        scale = scale
    )
}