package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class AddNewUserUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke() = repository.addNewUser()
}