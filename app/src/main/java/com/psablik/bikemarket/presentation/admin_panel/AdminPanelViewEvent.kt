package com.psablik.bikemarket.presentation.admin_panel

sealed interface AdminPanelViewEvent {
    object OrderStatusChangedSuccessfully : AdminPanelViewEvent
    object OrderStatusChangeFailed : AdminPanelViewEvent
}