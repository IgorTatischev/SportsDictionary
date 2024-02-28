package com.dictionary.sports.supabase.di

import com.dictionary.sports.supabase.BuildConfig
import com.dictionary.sports.supabase.repository.SupabaseRepository
import com.dictionary.sports.supabase.repository.SupabaseRepositoryImpl
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object SupabaseModule {

    operator fun invoke() = module {

        singleOf(::SupabaseRepositoryImpl) bind SupabaseRepository::class

        single {
            createSupabaseClient(
                supabaseUrl = BuildConfig.supabaseUrl,
                supabaseKey = BuildConfig.supabaseKey
            ) {
                defaultSerializer = KotlinXSerializer(Json {
                    ignoreUnknownKeys = true
                })
                install(Auth)
                install(Postgrest)
                install(Realtime)
                install(ComposeAuth) {
                    googleNativeLogin(serverClientId = BuildConfig.googleKey)
                }
            }
        }
    }
}