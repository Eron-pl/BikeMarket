package com.psablik.bikemarket.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.psablik.bikemarket.navigation.Screen
import com.psablik.bikemarket.presentation.components.BaseButton
import com.psablik.bikemarket.presentation.ui.theme.B1
import com.psablik.bikemarket.presentation.ui.theme.H2
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Variant
import com.psablik.bikemarket.utils.LaunchOnce
import kotlinx.coroutines.flow.collect

@Composable
fun SettingsView(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavController
) {

    LaunchedEffect(LaunchOnce) {
        viewModel.event.collect { event ->
            when (event) {
                is SettingsEvent.LoggedOut -> navController.navigate(Screen.Login.route)
            }
        }
    }

    Column(verticalArrangement = Arrangement.SpaceBetween) {
        TitleSection()

        LogoutButton(onClick = { viewModel.logOut() })
    }

}

@Composable
fun TitleSection() {
    Text(
        text = "Settings", // Todo: Strings
        style = H2,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )

    Spacer(Modifier.height(MaterialTheme.spacing.xs))
}

@Composable
fun LogoutButton(onClick: () -> Unit) {
    BaseButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.m)
    ) {
        Text(text = "Log out", style = B1) // Todo: String
    }
}