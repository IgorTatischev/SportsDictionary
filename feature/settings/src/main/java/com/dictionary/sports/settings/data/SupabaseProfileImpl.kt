package com.dictionary.sports.settings.data

import com.dictionary.sports.settings.data.model.ProfileEntity
import com.dictionary.sports.settings.domain.SupabaseProfile
import com.dictionary.sports.settings.domain.model.Profile
import com.dictionary.sports.supabase.repository.SupabaseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.put

internal class SupabaseProfileImpl(
    private val client: SupabaseClient,
    private val supabaseRepository: SupabaseRepository,
) : SupabaseProfile {

    override suspend fun deleteUser() {
        TODO()
    }

    override suspend fun signOut() = client.auth.signOut()

    override suspend fun changeUserData(
        login: String,
        userPassword: String,
        name: String,
    ): Result<Unit> = runCatching {

        val user = client.auth.retrieveUserForCurrentSession()

        if (login.isNotBlank()) {
            client.auth.modifyUser {
                email = login
            }
        }

        if (userPassword.isNotBlank()) {
            client.auth.modifyUser {
                password = userPassword
            }
        }

        if (name.isNotBlank()) {
            client.auth.modifyUser { data { put("name", name) } }
            client.from("profiles")
                .update(
                    { set("name", name) }
                ) {
                    filter {
                        eq("id", user.id)
                    }
                }
        }
    }

    override suspend fun getUserData(): Result<Profile> = runCatching {

        val user = client.auth.retrieveUserForCurrentSession()
        val email = user.email.toString()
        val profile = client.from("profiles")
            .select(
                columns = Columns.list("name"),
                request = {
                    filter {
                        eq("id", user.id)
                    }
                }
            ).decodeSingle<ProfileEntity>()

        Profile(
            email = email,
            name = profile.name,
        )
    }

}