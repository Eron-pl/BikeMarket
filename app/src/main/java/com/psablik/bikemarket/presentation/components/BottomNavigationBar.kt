package com.psablik.bikemarket.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.psablik.bikemarket.navigation.Screen

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val screenList = listOf(
        Screen.Home,
        Screen.Orders,
    )

    BottomNavigation() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screenList.forEach { screen ->
            val isSelected = currentRoute == screen.route
            if (screen.iconId != null && screen.title != null) {
                BottomNavigationItem(
                    selected = isSelected,
                    onClick = { navController.navigate(screen.route) },
                    selectedContentColor = MaterialTheme.colors.primary,
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