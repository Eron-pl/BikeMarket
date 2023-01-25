package com.psablik.bikemarket.presentation.settings

sealed interface SettingsEvent {
    object LoggedOut : SettingsEvent
}
