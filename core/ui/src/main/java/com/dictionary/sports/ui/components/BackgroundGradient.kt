package com.dictionary.sports.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.backgroundGradient(width: Float): Modifier {
    return this then Modifier
        .background(MaterialTheme.colorScheme.background)
        .background(
            brush = Brush.radialGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.tertiary,
                    Color.Transparent
                ),
                center = Offset.Infinite,
                radius = width
            )
        )
        .background(
            brush = Brush.radialGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.onBackground,
                    Color.Transparent
                ),
                center = Offset.Zero,
                radius = width
            )
        )
}