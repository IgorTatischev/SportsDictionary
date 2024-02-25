package com.dictionary.sports.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme = lightColorScheme(
    primary = white,
    background = mainBlue,
    onBackground = lightBlue,
    tertiary = darkBlue,
    outline = whiteTransparent,
    error = red,
    onPrimary = grayishBlue,
    primaryContainer = darkBlueSolid,
    onSecondary = grayishBlueLight
)

@Composable
fun DictionaryRushTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}