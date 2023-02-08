package com.psablik.bikemarket.domain.model

import com.google.errorprone.annotations.Keep

@Keep
data class Bike (
    val id: String,
    val imgPath: String,
    val name: String,
    val price: Int,
    val available: Boolean
)
