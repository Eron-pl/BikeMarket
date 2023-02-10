package com.psablik.bikemarket.presentation.payment

sealed interface PaymentEvent {
    object PaidAndPlacedOrder : PaymentEvent
    object PaymentFailed : PaymentEvent
}