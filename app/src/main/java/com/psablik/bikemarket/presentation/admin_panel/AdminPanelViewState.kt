package com.psablik.bikemarket.presentation.admin_panel

import com.psablik.bikemarket.domain.model.Order

sealed class AdminPanelViewState {
    object Idle : AdminPanelViewState()
    object Loading : AdminPanelViewState()
    data class Loaded(
        val orders: List<Order>
    ) : AdminPanelViewState()
    data class Error(
        val message: String
    ) : AdminPanelViewState()
}
