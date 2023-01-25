package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.domain.model.UserType
import com.psablik.bikemarket.repository.user.UserRepository
import javax.inject.Inject

class GetUserTypeUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): UserType = repository.getUserType()
}
