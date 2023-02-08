package com.psablik.bikemarket.infrastructure.model

import com.google.errorprone.annotations.Keep

@Keep
data class BikeResponse(
    val id: String? = null,
    val imgPath: String? = null,
    val name: String? = null,
    val price: Int? = null,
    val available: Boolean? = null
)
