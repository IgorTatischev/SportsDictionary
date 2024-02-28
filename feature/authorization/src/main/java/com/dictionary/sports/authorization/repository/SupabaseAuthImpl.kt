package com.dictionary.sports.authorization.repository

import com.dictionary.sports.supabase.repository.SupabaseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseAuthImpl(
    private val client: SupabaseClient,
    private val supabaseRepository: SupabaseRepository,
) : SupabaseAuth {

    override suspend fun signUp(
        navigateToScreen: () -> Unit,
        userLogin: String,
        userPassword: String,
    ): Result<Unit> = runCatching {

        val name = supabaseRepository.getCurrentUserName()

        client.auth.signUpWith(provider = Email) {
            email = userLogin.trim()
            password = userPassword.trim()
            data = buildJsonObject {
                put("name", name)
            }
        }

        supabaseRepository.saveToken()

        signIn(
            navigateToScreen = navigateToScreen,
            userLogin = userLogin,
            userPassword = userPassword
        )
    }

    override suspend fun signIn(
        navigateToScreen: () -> Unit,
        userLogin: String,
        userPassword: String,
    ): Result<Unit> = runCatching{

        client.auth.signInWith(provider = Email) {
            email = userLogin.trim()
            password = userPassword.trim()
        }
        supabaseRepository.saveToken()
        navigateToScreen()

    }
}