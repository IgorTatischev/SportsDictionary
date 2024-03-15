package com.dictionary.sports.authorization.domain.use_case

import com.dictionary.sports.common.datastore.DataStorePreferencesRepository
import com.dictionary.sports.supabase.repository.SupabaseRepository


internal class SaveTokenUseCase(
    private val supabaseRepository: SupabaseRepository,
    private val dataStorePreferencesRepository: DataStorePreferencesRepository,
) {

    suspend operator fun invoke() {
        val accessToken = supabaseRepository.getToken()
        accessToken?.let { dataStorePreferencesRepository.saveToken(token = it) }
    }
}