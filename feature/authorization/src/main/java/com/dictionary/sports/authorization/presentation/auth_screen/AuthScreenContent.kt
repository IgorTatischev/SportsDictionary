package com.dictionary.sports.authorization.presentation.auth_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dictionary.sports.authorization.presentation.auth_screen.components.Header
import com.dictionary.sports.authorization.presentation.auth_screen.components.SignUpContent
import com.dictionary.sports.authorization.presentation.auth_screen.viewmodel.AuthorizationViewModel
import com.dictionary.sports.authorization.presentation.auth_screen.viewmodel.UiEffect
import com.dictionary.sports.ui.components.Background
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AuthScreenContent(
    navigateToMenuScreen: () -> Unit,
    navigateBack: () -> Boolean,
    authorizationViewModel: AuthorizationViewModel,
) {

    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        authorizationViewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is UiEffect.NavigateToMenuScreen -> navigateToMenuScreen()
                is UiEffect.ShowSnackbar -> {
                    snackBarHostState.showSnackbar(
                        message =  context.resources.getString(effect.resId),
                    )
                }
            }
        }
    }

    Background(alpha = .6f)

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 26.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Header(navigateBack = navigateBack)

            SignUpContent(
                navigateToMenuScreen = navigateToMenuScreen,
                authorizationViewModel = authorizationViewModel
            )

            Spacer(modifier = Modifier.padding(bottom = 26.dp))
        }
    }
}