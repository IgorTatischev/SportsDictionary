package com.dictionary.sports.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BackgroundMain(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .backgroundGradient(10f)
    ) {
        BackgroundImage(alpha = 1f)
    }
}