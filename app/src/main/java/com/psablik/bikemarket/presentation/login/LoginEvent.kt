package com.psablik.bikemarket.presentation.login

sealed interface LoginEvent {
    object LoggedInUser : LoginEvent
    object LoggedInAdmin : LoginEvent
}
