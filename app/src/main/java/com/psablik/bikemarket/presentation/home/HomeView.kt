package com.psablik.bikemarket.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.psablik.bikemarket.presentation.components.BikeListColumn
import com.psablik.bikemarket.presentation.ui.theme.B7
import com.psablik.bikemarket.presentation.ui.theme.H2
import com.psablik.bikemarket.presentation.ui.theme.spacing
import com.psablik.bikemarket.ui.theme.Variant

@Composable
fun HomeView() {
    Column {

        val mockBikeList = listOf(  // Todo: Get from VM
            BikeOnView("Bike 1"),
            BikeOnView("Bike 2"),
            BikeOnView("Bike 3"),
            BikeOnView("Bike 4"),
            BikeOnView("Bike 5"),
            BikeOnView("Bike 6")
        )

        val bikeListState = rememberLazyListState()

        TitleSection()

        Spacer(Modifier.height(MaterialTheme.spacing.m))

        BikeListColumn(state = bikeListState, bikeList = mockBikeList)
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Our bikes", // Todo: Strings
        style = H2,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )

    Spacer(Modifier.height(MaterialTheme.spacing.xs))

    Text(
        text = "Choose one that suits you best", // Todo: Strings
        style = B7,
        color = Variant,
        modifier = Modifier.padding(start = MaterialTheme.spacing.m)
    )
}
