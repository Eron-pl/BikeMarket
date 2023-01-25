package com.psablik.bikemarket.domain.usecase

import com.google.firebase.auth.AuthCredential
import com.psablik.bikemarket.repository.AuthenticationRepository
import javax.inject.Inject

class SignInWithCredentialUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(
        credential: AuthCredential,
    ): Result<Unit> = repository.signInWithCredential(credential = credential)
}
