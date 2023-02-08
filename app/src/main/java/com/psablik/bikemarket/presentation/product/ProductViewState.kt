package com.psablik.bikemarket.presentation.product

sealed class ProductViewState {
    object Idle : ProductViewState()
    object Loading : ProductViewState()
    data class Loaded(
        val bikePrice: Int,
        val bikeName: String,
        val bikeDescription: String,
        val bikeImgPath: String
    ) : ProductViewState()
    data class Error(
        val message: String
    ) : ProductViewState()
}
