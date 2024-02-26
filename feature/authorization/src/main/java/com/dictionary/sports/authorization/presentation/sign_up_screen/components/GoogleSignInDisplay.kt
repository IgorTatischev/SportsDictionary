package com.dictionary.sports.authorization.presentation.sign_up_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dictionary.sports.authorization.R
import com.dictionary.sports.authorization.presentation.sign_up_screen.viewmode.UiEffect
import com.dictionary.sports.authorization.presentation.sign_up_screen.viewmode.AuthorizationViewModel
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GoogleSignInDisplay(
    navigateToMenuScreen: () -> Unit,
    authorizationViewModel: AuthorizationViewModel
) {

    LaunchedEffect(key1 = true) {
        authorizationViewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is UiEffect.NavigateToMenuScreen -> navigateToMenuScreen()
            }
        }
    }

    val errorText = authorizationViewModel.signInState.collectAsState().value.googleAuthError

    val authState =
        authorizationViewModel.supabaseClientFromVM.client.composeAuth.rememberSignInWithGoogle(
            onResult = { result ->
                authorizationViewModel.loginWithGoogle(result)
            }
        )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = errorText),
            style = MaterialTheme.typography.titleSmall,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.error
        )

        Text(
            text = stringResource(id = R.string.text_sign_variant),
            style = MaterialTheme.typography.titleSmall,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Image(
            modifier = Modifier
                .size(45.dp)
                .clip(shape = RoundedCornerShape(50))
                .clickable {
                    authState.startFlow()
                },
            painter = painterResource(id = R.drawable.google_icon),
            contentDescription = null
        )
    }
}