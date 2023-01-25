package com.psablik.bikemarket.repository.user

import com.psablik.bikemarket.domain.model.User
import com.psablik.bikemarket.domain.model.UserType

interface UserRepository {
    fun getCurrentUser(): User?
    suspend fun isUserRegistered(): Boolean
    suspend fun registerUser(): Result<Unit>
    suspend fun getUserType(): UserType
}