package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.repository.AuthenticationRepository
import javax.inject.Inject

class GetLoggedInStatusUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(): Boolean = repository.getLoggedInStatus()
}
