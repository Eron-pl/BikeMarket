package com.psablik.bikemarket.presentation.admin_panel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psablik.bikemarket.domain.usecase.ChangeOrderStatusUseCase
import com.psablik.bikemarket.domain.usecase.GetOrdersUseCase
import com.psablik.bikemarket.mapper.domain.OrderStatusMapper
import com.psablik.bikemarket.mapper.view.StatusOnViewMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class AdminPanelViewModel @Inject constructor(
    private val getOrders: GetOrdersUseCase,
    private val statusOnViewMapper: StatusOnViewMapper,
    private val changeOrderStatusUseCase: ChangeOrderStatusUseCase,
    private val statusMapper: OrderStatusMapper
) : ViewModel() {
    var state by mutableStateOf<AdminPanelViewState>(AdminPanelViewState.Idle)
        private set

    private val _event = MutableSharedFlow<AdminPanelViewEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch(IO) {
            loadData()
        }
    }

    private suspend fun loadData() {
        withContext(Main) {
            state = AdminPanelViewState.Loading
        }

        val orders = getOrders()

        withContext(Main) {
            state = AdminPanelViewState.Loaded(
                orders = orders.onEach { order ->
                    order.status = statusOnViewMapper(order.status)
                }
            )
        }
    }

    fun changeOrderStatus(orderId: String, status: String) {
        viewModelScope.launch(IO) {
            val result = changeOrderStatusUseCase(
                status = statusMapper(status),
                orderId = orderId
            )

            if (result) {
                _event.emit(AdminPanelViewEvent.OrderStatusChangedSuccessfully)
                loadData()
            } else {
                _event.emit(AdminPanelViewEvent.OrderStatusChangeFailed)
            }
        }
    }
}