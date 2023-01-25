package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.repository.user.UserRepository
import javax.inject.Inject

class IsUserRegisteredUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Boolean = repository.isUserRegistered()
}
