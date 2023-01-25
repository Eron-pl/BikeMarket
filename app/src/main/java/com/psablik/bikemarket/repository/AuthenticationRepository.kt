package com.psablik.bikemarket.repository

import com.psablik.bikemarket.domain.model.LoggedStatus

interface AuthenticationRepository {

    suspend fun clearLoggedInStatus()
    suspend fun getLoggedInStatus(): Boolean
    suspend fun setLoggedInStatus(status: LoggedStatus)

}