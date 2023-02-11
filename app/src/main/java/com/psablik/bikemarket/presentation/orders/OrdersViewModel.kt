package com.psablik.bikemarket.presentation.orders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psablik.bikemarket.domain.usecase.GetUserOrdersUseCase
import com.psablik.bikemarket.mapper.view.StatusOnViewMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getUserOrders: GetUserOrdersUseCase,
    private val statusOnViewMapper: StatusOnViewMapper
) : ViewModel() {

    var state by mutableStateOf<OrdersViewState>(OrdersViewState.Idle)
        private set

    init {
        state = OrdersViewState.Loading

        viewModelScope.launch(IO) {
            try {
                val orders = getUserOrders()

                withContext(Main) {
                    state = OrdersViewState.Loaded(
                        orders = orders.onEach { order ->
                            order.status = statusOnViewMapper(order.status)
                        }
                    )
                }
            } catch (e: Exception) {
                withContext(Main) {
                    state = OrdersViewState.Error("Error loading user's orders")
                }
            }
        }
    }
}