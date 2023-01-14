package com.psablik.bikemarket.navigation

import androidx.annotation.DrawableRes

sealed class Screen (
    @DrawableRes var iconId: Int? = null,
    var title: String? = null,
    val route: String
) {
    object Splash : Screen(route = "route_splash_screen")
    object Orders : Screen(route = "route_orders")
    object Settings : Screen(route = "route_settings")
    object Home : Screen(route = "route_home")
}
