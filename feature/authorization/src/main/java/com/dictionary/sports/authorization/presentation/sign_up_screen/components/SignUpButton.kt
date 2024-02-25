package com.dictionary.sports.authorization.presentation.sign_up_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.R

@Composable
fun SignUpButton(
    action: () -> Unit,
    textRes: Int = R.string.button_sign_up
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { action() },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, end = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.icon_person_add),
                contentDescription = null
            )

            Text(
                text = stringResource(id = textRes),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}