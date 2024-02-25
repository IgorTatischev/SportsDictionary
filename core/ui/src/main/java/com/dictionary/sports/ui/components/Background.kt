package com.dictionary.sports.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.dictionary.sports.resources.R as CoreRes

@Composable
fun Background(
    alpha: Float = 1f,
    addIcon: Boolean = false,
    scale: Animatable<Float, AnimationVector1D> = Animatable(1f)
) {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels

    Box(
        modifier = Modifier
            .fillMaxSize()
            .backgroundGradient(width = width.toFloat())
    ) {
        BackgroundImage(alpha = alpha)

        if (addIcon)
            Image(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .align(Alignment.Center)
                    .scale(scale.value),
                painter = painterResource(id = CoreRes.drawable.icon_tennis),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
    }
}