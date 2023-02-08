package com.psablik.bikemarket.mapper.domain

import com.psablik.bikemarket.domain.model.Bike
import com.psablik.bikemarket.infrastructure.model.BikeResponse

class BikeMapper  {
    operator fun invoke(bike: BikeResponse): Bike = with (bike) {
        Bike(
            id = id ?: "",
            imgPath = imgPath ?: ERROR_IMG_URL,
            name = name ?: "",
            price = price ?: 0,
            available = available ?: false
        )
    }

    companion object {
        private const val ERROR_IMG_URL = "https://developers.google.com/maps/documentation/streetview/error-messages"
    }
}