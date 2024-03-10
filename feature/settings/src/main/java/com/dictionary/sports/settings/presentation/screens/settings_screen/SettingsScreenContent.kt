package com.dictionary.sports.settings.presentation.screens.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dictionary.sports.common.locale.AppLanguage
import com.dictionary.sports.settings.R
import com.dictionary.sports.settings.presentation.components.AccountActionsButtons
import com.dictionary.sports.settings.presentation.components.ChangeNameDialog
import com.dictionary.sports.settings.presentation.components.LanguageSpinner
import com.dictionary.sports.settings.presentation.components.SettingsButton
import com.dictionary.sports.settings.presentation.components.SettingsTopBar
import com.dictionary.sports.ui.components.BackgroundMain
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreenContent(
    screenModel: SettingsScreenModel,
    navigateBack: () -> Boolean,
    navigateToAuthScreen: () -> Unit,
) {
    val context = LocalContext.current
    val state = screenModel.state.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    if (state.showDialog)
        ChangeNameDialog(
            screenModel = screenModel
        )

    LaunchedEffect(key1 = true) {
        screenModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is UiEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = context.getString(effect.resId))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            SettingsTopBar {
                navigateBack()
            }
        },
        floatingActionButton = {
            AccountActionsButtons(
                onDeleteAccountClick = {
                    screenModel.deleteUser()
                    navigateToAuthScreen()
                },
                onSignInClick = {
                    screenModel.signOut()
                    navigateToAuthScreen()
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->

        BackgroundMain()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Transparent)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SettingsButton(text = stringResource(id = R.string.write_us)) {
                screenModel.openEmail()
            }

            SettingsButton(text = stringResource(id = R.string.rate_us)) {
                screenModel.openMarket()
            }

            SettingsButton(text = stringResource(id = R.string.change_name)) {
                screenModel.showDialog()
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 15.dp, bottom = 5.dp),
                text = stringResource(id = R.string.language),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            LanguageSpinner(
                modifier = Modifier.padding(start = 15.dp),
                languages = AppLanguage.values().toList(),
                selected = state.selectedLanguages
            ) {
                screenModel.changeLanguage(it)
            }
        }
    }
}