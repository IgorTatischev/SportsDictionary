package com.dictionary.sports.authorization.domain.use_case

import com.dictionary.sports.authorization.domain.repository.SupabaseAuth

internal class SignInUseCase(
    private val authRepository: SupabaseAuth,
    private val saveTokenUseCase: SaveTokenUseCase,
) {

    suspend operator fun invoke(
        login: String,
        userPassword: String,
    ): Result<Unit> {

        return authRepository.signIn(
            userLogin = login,
            userPassword = userPassword,
        ).onSuccess {
            saveTokenUseCase()
        }
    }
}