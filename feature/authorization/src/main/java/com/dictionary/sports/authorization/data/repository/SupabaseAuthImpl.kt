package com.dictionary.sports.authorization.data.repository

import com.dictionary.sports.authorization.domain.repository.SupabaseAuth
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class SupabaseAuthImpl(private val client: SupabaseClient) : SupabaseAuth {

    override suspend fun signUp(
        userLogin: String,
        userPassword: String,
    ): Result<Unit> = runCatching {

        val name = userLogin.substringBefore('@')

        client.auth.signUpWith(provider = Email) {
            email = userLogin.trim()
            password = userPassword.trim()
            data = buildJsonObject {
                put("name", name)
            }
        }
    }

    override suspend fun signIn(
        userLogin: String,
        userPassword: String,
    ): Result<Unit> = runCatching{

        client.auth.signInWith(provider = Email) {
            email = userLogin.trim()
            password = userPassword.trim()
        }
    }
}