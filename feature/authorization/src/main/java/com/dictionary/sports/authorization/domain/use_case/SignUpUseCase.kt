package com.dictionary.sports.authorization.domain.use_case

import com.dictionary.sports.authorization.domain.repository.SupabaseAuth


internal class SignUpUseCase(
    private val authRepository: SupabaseAuth,
    private val signInUseCase: SignInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
) {

    suspend operator fun invoke(
        login: String,
        userPassword: String,
    ): Result<Unit> {

        return authRepository.signUp(
            userLogin = login,
            userPassword = userPassword,
        ).onSuccess {
            saveTokenUseCase()
            signInUseCase(login = login, userPassword = userPassword)
        }
    }
}