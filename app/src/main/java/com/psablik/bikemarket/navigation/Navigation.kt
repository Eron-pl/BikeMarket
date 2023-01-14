package com.psablik.bikemarket.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.psablik.bikemarket.R
import com.psablik.bikemarket.presentation.home.HomeView
import com.psablik.bikemarket.presentation.orders.OrdersView
import com.psablik.bikemarket.presentation.settings.SettingsView
import com.psablik.bikemarket.presentation.splash.SplashView

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    modifier: Modifier,
    navController: NavHostController
) {

    Screen.Home.apply {
        title = "Home"
        iconId = R.drawable.ic_home
    }

    Screen.Orders.apply {
        title = "Orders"
        iconId = R.drawable.ic_bike
    }

    Screen.Settings.apply {
        title = "Settings"
        iconId = R.drawable.ic_settings
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        // Home
        composable(
            route = Screen.Home.route
        ) {
            HomeView()
        }

        // Splash
        composable(
            route = Screen.Splash.route
        ) {
            SplashView()
        }

        // Orders
        composable(
            route = Screen.Orders.route
        ) {
            OrdersView()
        }

        // Settings
        composable(
            route = Screen.Settings.route
        ) {
            SettingsView()
        }
    }
}
