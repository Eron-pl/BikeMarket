package com.psablik.bikemarket.mapper.domain

class UserTypeMapper {
    operator fun invoke(type: String?): UserType =
        when (type) {
            "admin" -> UserType.ADMIN
            else -> UserType.USER
        }
}