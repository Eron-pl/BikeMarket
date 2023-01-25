package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.repository.AuthenticationRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke() {
        with (repository) {
            logOut()
            clearLoggedInStatus()
        }
    }
}
