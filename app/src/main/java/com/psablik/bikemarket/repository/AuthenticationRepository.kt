package com.psablik.bikemarket.repository

import com.google.firebase.auth.AuthCredential
import com.psablik.bikemarket.domain.model.LoggedStatus
import com.psablik.bikemarket.domain.model.User

interface AuthenticationRepository {
    fun getCurrentUser(): User?
    suspend fun logOut(): Result<Unit>
    suspend fun signInWithCredential(credential: AuthCredential): Result<Unit>
    suspend fun clearLoggedInStatus()
    suspend fun getLoggedInStatus(): Boolean
    suspend fun setLoggedInStatus(status: LoggedStatus)

}