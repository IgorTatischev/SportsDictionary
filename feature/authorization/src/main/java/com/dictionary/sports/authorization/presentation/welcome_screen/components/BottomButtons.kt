package com.dictionary.sports.authorization.presentation.welcome_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.R
import com.dictionary.sports.authorization.presentation.auth_screen.components.AuthButton
import com.dictionary.sports.ui.theme.grayBlueButtonColor

@Composable
internal fun BottomButtons(
    navigateToRegistrationScreen: () -> Unit,
    navigateToMenuScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AuthButton(action = navigateToRegistrationScreen)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(37.dp)
                .clip(shape = RoundedCornerShape(50)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(50))
                    .blur(20.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(grayBlueButtonColor)
                    .clickable { navigateToMenuScreen() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 26.dp),
                    painter = painterResource(id = R.drawable.icon_skip),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = stringResource(id = R.string.button_skip),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}