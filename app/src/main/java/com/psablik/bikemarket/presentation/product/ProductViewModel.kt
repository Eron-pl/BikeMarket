package com.psablik.bikemarket.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.psablik.bikemarket.domain.usecase.GetBikeUseCase
import com.psablik.bikemarket.infrastructure.local.MockData
import com.psablik.bikemarket.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getBike: GetBikeUseCase
) : ViewModel() {
    private lateinit var productId: String

    var state by mutableStateOf<ProductViewState>(ProductViewState.Idle)
        private set

    private val _event = MutableSharedFlow<ProductEvent>()
    val event = _event.asSharedFlow()

    suspend fun init(id: String) {
        state = ProductViewState.Loading
        productId = id

        viewModelScope.launch {
            try {
                val bike = getBike(productId)

                withContext(Main) {
                    state = ProductViewState.Loaded(
                        bikeDescription = MockData.bikeDesciption,
                        bikeImgPath = bike.imgPath,
                        bikePrice = bike.price,
                        bikeName = bike.name
                    )
                }
            } catch (e: Exception) {
                withContext(Main) {
                    state = ProductViewState.Error("Error while loading product's data")
                }
            }
        }
    }

    fun navigateToPayment(navController: NavController) {
        navController.navigate(
            route = Screen.Payment.route
                .replace(
                    oldValue = Screen.PRODUCT_ID_ARG_KEY,
                    newValue = productId
                )
        )
    }

    fun buyProduct() {
        viewModelScope.launch {
            _event.emit(ProductEvent.BoughtProduct)
        }
    }
}