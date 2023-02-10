package com.psablik.bikemarket.domain.error

object WrongBikeIdException : Exception("Bike with that id was not found")
class LoadingDataFromFirestoreException(override val message: String) : Exception(message)