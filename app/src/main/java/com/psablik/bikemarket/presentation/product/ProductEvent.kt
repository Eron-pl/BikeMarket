package com.psablik.bikemarket.presentation.product

sealed interface ProductEvent {
    object BoughtProduct : ProductEvent
}
