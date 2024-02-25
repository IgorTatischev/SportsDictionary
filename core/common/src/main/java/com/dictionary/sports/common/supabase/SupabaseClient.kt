package com.dictionary.sports.common.supabase

import com.dictionary.sports.common.BuildConfig
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json

class SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = BuildConfig.supabaseUrl,
        supabaseKey = BuildConfig.supabaseKey
    ) {
        defaultSerializer = KotlinXSerializer(Json {
            ignoreUnknownKeys = true
        })

        install(GoTrue) {
            jwtToken = BuildConfig.jwtToken
        }
        install(ComposeAuth) {
            googleNativeLogin(serverClientId = BuildConfig.googleKey)
        }

        install(Postgrest)
    }
}