package com.dictionary.sports.dictionary.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dictionary.sports.ui.theme.whiteGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MenuTopBar(scrollBehavior: TopAppBarScrollBehavior, action: () -> Unit) {
    TopAppBar(
        title = {},
        actions = {
            FilledTonalIconButton(
                onClick = action,
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = whiteGradient,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        scrollBehavior = scrollBehavior
    )
}