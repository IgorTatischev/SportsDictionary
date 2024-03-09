package com.dictionary.sports.dictionary.presentation.screens.sport_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.dictionary.sports.common.sports.Sports
import com.dictionary.sports.dictionary.presentation.components.DescriptionScaffold
import com.dictionary.sports.dictionary.presentation.components.SportCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportDescriptionScreenContent(
    item: Sports,
    navigateBack: () -> Boolean,
    navigateToComments: (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    DescriptionScaffold(
        scrollBehavior = scrollBehavior,
        navigateBack = {
            navigateBack()
        },
        navigateToComments = {
            navigateToComments(item.filterValue)
        },
    ) { padding ->

        Box(Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = item.backgroundResId),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
        }

        val terms = stringArrayResource(id = item.termsResId)

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                SportCard(
                    color = item.color,
                    iconResId = item.iconResId,
                    nameResId = item.nameResId
                )
            }

            items(
                items = terms,
                key = {
                    it
                }
            ) { text ->
                TermsCard(text)
            }
        }
    }
}

@Composable
fun TermsCard(text: String) {
    Card(
        modifier = Modifier.padding(horizontal = 15.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Gray.copy(alpha = 0.4f),
                            Color.White.copy(alpha = 0.5f)
                        ),
                        startY = 0.0f,
                        endY = 600.0f
                    ),
                    shape = RoundedCornerShape(25.dp)
                )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                text = text,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}