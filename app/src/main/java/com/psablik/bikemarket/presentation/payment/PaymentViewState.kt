package com.psablik.bikemarket.presentation.payment

sealed class PaymentViewState {
    object Idle : PaymentViewState()
    object Loading: PaymentViewState()
    data class Loaded(
        val isPayButtonEnabled : Boolean = true,
        val bikePrice: Int,
        val bikeName: String,
        val bikeImgPath: String
    ) : PaymentViewState()

    data class Error(val message: String) : PaymentViewState()
}
