package com.psablik.bikemarket.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.psablik.bikemarket.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val screenList = listOf(
        Screen.Home,
        Screen.Orders,
    )

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screenList.forEach { screen ->
            val isSelected = currentRoute == screen.route
            if (screen.iconId != null && screen.title != null) {
                BottomNavigationItem(
                    selected = isSelected,
                    onClick = { navController.navigate(screen.route) {
                        popUpTo(Screen.Home.route) {
                            saveState
                        }
                        launchSingleTop = true
                        restoreState = true
                    } },
                    selectedContentColor = Color.Red, // Todo: check why material theme not working
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.iconId!!),
                            contentDescription = "" // TODO : Add description
                        )
                    },
                    label = { Text(text = screen.title!!) }
                )
            }
        }
    }
}
