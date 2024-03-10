package com.dictionary.sports.authorization.presentation.auth_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dictionary.sports.authorization.R
import com.dictionary.sports.authorization.presentation.auth_screen.AuthorizationScreenModel
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth

@Composable
internal fun GoogleSignInDisplay(
    screenModel: AuthorizationScreenModel,
) {

    val authState = screenModel.googleClient.composeAuth.rememberSignInWithGoogle(
        onResult = { result ->
            screenModel.loginWithGoogle(result)
        }
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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