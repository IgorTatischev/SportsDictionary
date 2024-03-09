package com.dictionary.sports.authorization.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.dictionary.sports.ui.components.Background
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreenContent(
    navigateToWelcomeScreen: () -> Unit,
    navigateToMenuScreen: () -> Unit,
    splashViewModel: SplashViewModel,
) {
    val scale = remember {
        Animatable(0f)
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
        splashViewModel.isUserLoggedIn()
    }

    LaunchedEffect(key1 = Unit) {
        splashViewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is SplashScreenSideEffect.MoveToMenu -> navigateToMenuScreen()
                is SplashScreenSideEffect.MoveToAuth -> navigateToWelcomeScreen()
            }
        }
    }

    Background(
        addIcon = true,
        scale = scale
    )
}