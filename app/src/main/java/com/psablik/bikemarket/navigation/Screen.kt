package com.psablik.bikemarket.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.*

sealed class Screen(
    @DrawableRes var iconId: Int? = null,
    var title: String? = null,
    val route: String,
    val arguments: List<NamedNavArgument>? = null
) {
    object Splash : Screen(route = "route_splash_screen")
    object Orders : Screen(route = "route_orders")
    object Login : Screen(route = "route_login")
    object Settings : Screen(route = "route_settings")
    object Home : Screen(route = "route_home")
    object Product : Screen(
        route = "route_product?productId=$PRODUCT_ID_ARG_KEY",
        arguments = listOf(
            navArgument("productId") { type = NavType.StringType }
        ))

    companion object {
        const val PRODUCT_ID_ARG_KEY = "{productId}"
    }
}
