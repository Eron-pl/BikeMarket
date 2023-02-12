package com.psablik.bikemarket.domain.usecase

import com.psablik.bikemarket.mapper.domain.UserType
import com.psablik.bikemarket.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class GetUserTypeUseCase @Inject constructor(
  private val repository: AuthenticationRepository,
  private val getUserId: GetUserIdUseCase
) {
    suspend operator fun invoke(): UserType = repository.getCurrentUserType(getUserId())
}