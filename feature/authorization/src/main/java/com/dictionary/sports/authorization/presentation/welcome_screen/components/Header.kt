package com.dictionary.sports.authorization.presentation.welcome_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.R

@Composable
internal fun Header() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_header_text),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )

        Text(
            text = stringResource(id = R.string.welcome_description_text),
            style = MaterialTheme.typography.titleSmall,
            color = Color.White
        )
    }
}