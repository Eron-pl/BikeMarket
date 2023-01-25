package com.psablik.bikemarket.mapper.domain

import com.psablik.bikemarket.domain.model.UserType

class UserTypeMapper {
    operator fun invoke(type: String?): UserType =
        when (type) {
            "admin" -> UserType.ADMIN
            else -> UserType.USER
        }
}
