package com.dictionary.sports.dictionary.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SportCard(color: Color, iconResId: Int, nameResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(horizontal = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(
            width = 2.dp,
            brush = Brush.verticalGradient(
                colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent),
                startY = 100.0f,
                endY = 400f
            )
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, start = 15.dp, bottom = 5.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Icon(painter = painterResource(id = iconResId), contentDescription = null)
            Text(
                text = stringResource(id = nameResId),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

//without click for description
@Composable
fun SportCard(color: Color, iconResId: Int, nameResId: Int) {
    Card(
        modifier = Modifier.padding(horizontal = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(
            width = 2.dp,
            brush = Brush.verticalGradient(
                colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent),
                startY = 100.0f,
                endY = 400f
            )
        ),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, start = 15.dp, bottom = 5.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Icon(painter = painterResource(id = iconResId), contentDescription = null)
            Text(
                text = stringResource(id = nameResId),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}