package com.psablik.bikemarket.repository.authentication

import com.google.firebase.auth.AuthCredential
import com.psablik.bikemarket.domain.model.LoggedStatus
import com.psablik.bikemarket.domain.model.User
import com.psablik.bikemarket.mapper.domain.UserType

interface AuthenticationRepository {
    fun getCurrentUser(): User?
    suspend fun logOut(): Result<Unit>
    suspend fun signInWithCredential(credential: AuthCredential): Result<Unit>
    suspend fun clearLoggedInStatus()
    suspend fun getLoggedInStatus(): LoggedStatus
    suspend fun setLoggedInStatus(status: LoggedStatus)
    suspend fun getCurrentUserType(userId: String): UserType
    suspend fun checkIfUserExists(): Boolean
    suspend fun addNewUser()
}
