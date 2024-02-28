package com.dictionary.sports.settings.repository

import com.dictionary.sports.supabase.repository.SupabaseRepository
import io.github.jan.supabase.SupabaseClient

internal class SupabaseProfileImpl(
    private val client: SupabaseClient,
    private val supabaseRepository: SupabaseRepository,
) : SupabaseProfile {

    override suspend fun deleteUser(): Result<Unit> = runCatching {

    }

    override suspend fun changeProfileName(name: String): Result<Unit> = runCatching {
//        val userId = supabaseClient.client.auth.retrieveUserForCurrentSession()
//
//        supabaseClient.client.auth.admin.updateUserById(userId.id) {
//            userMetadata = buildJsonObject {
//                put("name", newName)
//            }
//        }
//
//        val user = client.auth.modifyUser {
//            email = "newEmail@email.com"
//        }
    }

}