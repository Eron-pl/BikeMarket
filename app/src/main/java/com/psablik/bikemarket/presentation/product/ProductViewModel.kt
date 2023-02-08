package com.psablik.bikemarket.presentation.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psablik.bikemarket.domain.usecase.GetBikeUseCase
import com.psablik.bikemarket.infrastructure.local.MockData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getBike: GetBikeUseCase
) : ViewModel() {
    private lateinit var productId: String

    var state by mutableStateOf<ProductViewState>(ProductViewState.Idle)
        private set

    suspend fun init(id: String) {
        productId = id
        state = ProductViewState.Loading

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
}