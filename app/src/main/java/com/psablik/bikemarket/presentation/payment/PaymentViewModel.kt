package com.psablik.bikemarket.presentation.payment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psablik.bikemarket.domain.usecase.BuyProductUseCase
import com.psablik.bikemarket.domain.usecase.GetBikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val getBike: GetBikeUseCase,
    private val buyProduct: BuyProductUseCase
) : ViewModel() {
    private lateinit var productId: String

    var state by mutableStateOf<PaymentViewState>(PaymentViewState.Idle)
        private set

    private val _event = MutableSharedFlow<PaymentEvent>()
    val event = _event.asSharedFlow()

    suspend fun init(id: String) {
        state = PaymentViewState.Loading
        productId = id

        viewModelScope.launch(IO) {
            try {
                val bike = getBike(productId)

                withContext(Main) {
                    with(bike) {
                        state = PaymentViewState.Loaded(
                            bikePrice = price,
                            bikeImgPath = imgPath,
                            bikeName = name
                        )
                    }
                }
            } catch (e: Exception) {
                withContext(Main) {
                    state = PaymentViewState.Error("Error when loading product's basket")
                }
            }
        }
    }

    fun payAndPlaceOrder() {
        viewModelScope.launch(IO) {
           if (buyProduct(productId)) {
               _event.emit(PaymentEvent.PaidAndPlacedOrder)
           } else {
               _event.emit(PaymentEvent.PaymentFailed)
           }
        }
    }
}
