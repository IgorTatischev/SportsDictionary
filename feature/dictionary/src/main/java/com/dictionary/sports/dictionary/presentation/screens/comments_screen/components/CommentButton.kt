package com.dictionary.sports.dictionary.presentation.screens.comments_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.dictionary.sports.dictionary.R
import com.dictionary.sports.dictionary.presentation.screens.comments_screen.viewmodel.CommentsViewModel

@Composable
fun CommentButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(bottom = 6.dp),
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_comment),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = stringResource(id = R.string.leave_comment),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}