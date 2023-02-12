package com.psablik.bikemarket.presentation.main_activity

sealed class MainActivityViewState {
    object Idle : MainActivityViewState()
    object Loading : MainActivityViewState()
    data class Loaded(
        val isAdmin: Boolean
    ) : MainActivityViewState()
    data class Error(
        val message: String
    ) : MainActivityViewState()
}
