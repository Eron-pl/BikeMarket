package com.psablik.bikemarket.domain.model

data class Basket(
    val bikes: List<Bike>,
    val totalPrice: Int
)
