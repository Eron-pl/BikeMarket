package com.psablik.bikemarket.presentation.home

import com.psablik.bikemarket.domain.model.Bike

sealed class HomeViewState {
    object Idle : HomeViewState()
    object Loading : HomeViewState()
    data class Loaded(
        val bikes: List<Bike>
    ) : HomeViewState()
    data class Error(
        val message: String
    ) : HomeViewState()
}
