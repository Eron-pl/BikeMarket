package com.psablik.bikemarket.mapper.domain

import com.psablik.bikemarket.domain.model.LoggedStatus
import javax.inject.Inject

class LoggedStatusMapper @Inject constructor() {
    operator fun invoke(status: LoggedStatus): Boolean =
        when (status) {
            LoggedStatus.LOGGED_IN -> true
            LoggedStatus.NOT_LOGGED_IN -> false
        }

    operator fun invoke(status: Boolean): LoggedStatus =
        when (status) {
            true -> LoggedStatus.LOGGED_IN
            false -> LoggedStatus.NOT_LOGGED_IN
        }
    }
