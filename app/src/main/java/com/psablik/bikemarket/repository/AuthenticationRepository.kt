package com.psablik.bikemarket.repository

interface AuthenticationRepository {

    suspend fun clearLoggedInStatus()
    suspend fun getLoggedInStatus(): Boolean
    suspend fun setLoggedInStatus(status: Boolean)

}