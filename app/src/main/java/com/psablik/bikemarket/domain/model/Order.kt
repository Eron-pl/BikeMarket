package com.psablik.bikemarket.domain.model

data class Order(
    val id: String,
    val bike: Bike,
    var status: String,
    val user: User
)
