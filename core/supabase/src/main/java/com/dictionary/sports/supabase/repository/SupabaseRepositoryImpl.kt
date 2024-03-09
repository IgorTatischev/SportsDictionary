package com.dictionary.sports.supabase.repository

import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.supabase.state.LoggedInState
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import org.json.JSONObject

internal class SupabaseRepositoryImpl(
    private val client: SupabaseClient,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) : SupabaseRepository {

    override suspend fun saveToken() {
        val accessToken = client.auth.currentAccessTokenOrNull()
        accessToken?.let {
            dataStorePreferencesRepository.saveToken(token = accessToken)
        }
    }

    override suspend fun getCurrentUserName(): String {

        val user = client.auth.retrieveUserForCurrentSession()
        val userEmail = user.email ?: ""
        var name = userEmail.substringBefore('@')

        user.userMetadata?.let {
            val jsonObject = JSONObject(it.toString())
            name = jsonObject.getString("name")
        }

        return name
    }

    override suspend fun isUserLoggedIn(token: String): LoggedInState {
        return try {
            if (token.isNotEmpty()) {
                client.auth.retrieveUser(token)
                client.auth.refreshCurrentSession()
                saveToken()
                LoggedInState.Success(isLoggedIn = true)
            } else {
                LoggedInState.Success(isLoggedIn = false)
            }
        } catch (e: Exception) {
            LoggedInState.Error(message = e.message.orEmpty())
        }
    }
}