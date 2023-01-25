package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.repository.user.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Result<Unit> = repository.registerUser()
}
