package com.dictionary.sports.supabase.repository

import com.dictionary.sports.supabase.state.LoggedInStatus
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject

internal class SupabaseRepositoryImpl(private val client: SupabaseClient) : SupabaseRepository {

    override suspend fun getToken(): String? {
        return client.auth.currentAccessTokenOrNull()
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

    override val isUserLoggedIn: Flow<LoggedInStatus> = flow {

        client.auth.sessionStatus.collect {
            when (it) {
                is SessionStatus.Authenticated ->
                    emit(LoggedInStatus.Success(isLoggedIn = true))
                //saveToken() not needed yet

                is SessionStatus.LoadingFromStorage -> {
                    //saveToken()
                    client.auth.refreshCurrentSession()
                    emit(LoggedInStatus.Success(isLoggedIn = false))
                }

                is SessionStatus.NetworkError ->
                    emit(LoggedInStatus.Error(message = "Network Error"))

                is SessionStatus.NotAuthenticated -> emit(LoggedInStatus.Success(isLoggedIn = false))
            }
        }
    }.flowOn(Dispatchers.IO)

}