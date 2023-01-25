package com.psablik.bikemarket.presentation.login

sealed interface LoginEvent {
    object LoggedIn : LoginEvent
}
