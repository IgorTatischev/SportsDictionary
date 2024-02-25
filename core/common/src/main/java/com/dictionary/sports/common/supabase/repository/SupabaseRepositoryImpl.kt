package com.dictionary.sports.common.supabase.repository

import android.content.Context
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.supabase.SupabaseClient
import com.dictionary.sports.common.supabase.state.AuthResult
import com.dictionary.sports.common.supabase.state.ChangeUserDataState
import com.dictionary.sports.common.supabase.state.LoggedInState
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.json.JSONObject
import com.dictionary.sports.resources.R as CoreRes

internal class SupabaseRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val context: Context,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository
) : SupabaseRepository {

    override suspend fun signUp(
        navigateToScreen: () -> Unit, userLogin: String, userPassword: String
    ): AuthResult {
        try {
            supabaseClient.client.gotrue.admin.createUserWithEmail {
                email = userLogin.trim()
                password = userPassword.trim()
                autoConfirm = true

                userMetadata = buildJsonObject {
                    put(key = "name",value = userLogin.trim().substringBefore('@'))
                }
            }

            saveToken()

            login(
                navigateToScreen = navigateToScreen,
                userLogin = userLogin,
                userPassword = userPassword
            )

            return AuthResult.Success(successMessageRes = CoreRes.string.sign_up_success)
        } catch (e: Exception) {
            e.message?.let {
                return AuthResult.Error(errorMessage = it)
            } ?: return AuthResult.Error(errorMessage = context.getString(CoreRes.string.error))
        }
    }

    override suspend fun login(
        navigateToScreen: () -> Unit, userLogin: String, userPassword: String
    ): AuthResult {
        try {
            supabaseClient.client.gotrue.loginWith(provider = Email) {
                email = userLogin.trim()
                password = userPassword.trim()
            }
            saveToken()
            navigateToScreen()

            return AuthResult.Success(successMessageRes = CoreRes.string.log_in_success)
        } catch (e: Exception) {
            e.message?.let {
                return AuthResult.Error(errorMessage = it)
            } ?: return AuthResult.Error(errorMessage = context.getString(CoreRes.string.error))
        }
    }

    override suspend fun saveToken() {
        val accessToken = supabaseClient.client.gotrue.currentAccessTokenOrNull()
        accessToken?.let {
            dataStorePreferencesRepository.saveToken(token = accessToken)
        }
    }

    override suspend fun deleteUser(): ChangeUserDataState {
        return try {
            val userId = supabaseClient.client.gotrue.retrieveUserForCurrentSession()
            supabaseClient.client.gotrue.admin.deleteUser(userId.id)

            dataStorePreferencesRepository.saveToken(token = "")

            ChangeUserDataState.Success
        } catch (e: Exception) {
            ChangeUserDataState.Error
        }
    }

    override suspend fun getCurrentUserName(): String {
        return try {
            val currentUser = supabaseClient.client.gotrue.retrieveUserForCurrentSession()

            val userEmail = currentUser.email ?: ""
            var name = userEmail.substringBefore('@')

            currentUser.userMetadata?.let {
                val jsonObject = JSONObject(it.toString())
                name = jsonObject.getString("name")
            }

            name
        } catch (e: Exception) {
            ""
        }
    }

    override suspend fun saveNewNameForUser(newName: String): ChangeUserDataState {
        return try {
            val userId = supabaseClient.client.gotrue.retrieveUserForCurrentSession()

            supabaseClient.client.gotrue.admin.updateUserById(userId.id) {
                userMetadata = buildJsonObject {
                    put("name", newName)
                }
            }

            ChangeUserDataState.Success
        } catch (e: Exception) {
            ChangeUserDataState.Error
        }
    }

    override suspend fun isUserLoggedIn(token: String): LoggedInState {
        return try {
            if (token.isNotEmpty()) {
                supabaseClient.client.gotrue.retrieveUser(token)
                supabaseClient.client.gotrue.refreshCurrentSession()
                saveToken()
                LoggedInState.Success(isLoggedIn = true)
            } else {
                LoggedInState.Success(isLoggedIn = false)
            }
        } catch (e: Exception) {
            LoggedInState.Error
        }
    }

}