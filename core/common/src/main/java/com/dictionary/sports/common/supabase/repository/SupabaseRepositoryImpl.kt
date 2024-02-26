package com.dictionary.sports.common.supabase.repository

import android.content.Context
import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.common.supabase.SupabaseClient
import com.dictionary.sports.common.supabase.state.AuthResult
import com.dictionary.sports.common.supabase.state.ChangeUserDataState
import com.dictionary.sports.common.supabase.state.LoggedInState
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
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
            supabaseClient.client.auth.signUpWith(provider = Email){
                email = userLogin.trim()
                password = userPassword.trim()
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
            supabaseClient.client.auth.signInWith(provider = Email) {
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
        val accessToken = supabaseClient.client.auth.currentAccessTokenOrNull()
        accessToken?.let {
            dataStorePreferencesRepository.saveToken(token = accessToken)
        }
    }

    //todo
    override suspend fun getCurrentUserName(): String {
        return try {
            val currentUser = supabaseClient.client.auth.retrieveUserForCurrentSession()

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
            //todo update user name
//            val userId = supabaseClient.client.auth.retrieveUserForCurrentSession()
//
//            supabaseClient.client.auth.admin.updateUserById(userId.id) {
//                userMetadata = buildJsonObject {
//                    put("name", newName)
//                }
//            }

            val user = supabaseClient.client.auth.modifyUser {
                email = "newEmail@email.com"
            }

            ChangeUserDataState.Success
        } catch (e: Exception) {
            ChangeUserDataState.Error
        }
    }

    override suspend fun isUserLoggedIn(token: String): LoggedInState {
        return try {
            if (token.isNotEmpty()) {
                supabaseClient.client.auth.retrieveUser(token)
                supabaseClient.client.auth.refreshCurrentSession()
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