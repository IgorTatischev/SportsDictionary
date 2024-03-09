package com.dictionary.sports.dictionary.presentation.screens.menu_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dictionary.sports.common.sports.Sports
import com.dictionary.sports.dictionary.presentation.components.MenuTopBar
import com.dictionary.sports.dictionary.presentation.components.SportCard
import com.dictionary.sports.ui.components.BackgroundMain
import com.dictionary.sports.resources.R as CoreRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreenContent(
    navigateToSettings: () -> Unit,
    navigateToSportScreen: (Sports) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            MenuTopBar(scrollBehavior) {
                navigateToSettings()
            }
        }
    ) { padding ->

        BackgroundMain()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Transparent)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, top = 15.dp),
                    text = stringResource(id = CoreRes.string.sports),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            items(items = Sports.values()) { item ->
                SportCard(item.color, item.iconResId, item.nameResId) {
                    navigateToSportScreen(item)
                }
            }
        }
    }
}