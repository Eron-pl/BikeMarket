package com.psablik.bikemarket.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.psablik.bikemarket.domain.model.Bike
import com.psablik.bikemarket.presentation.ui.theme.spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BikeListColumn(
    state: LazyListState,
    bikeList: List<Bike>,

    ) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = state,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.m),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.m)
    ) {
        items(items = bikeList, key = { it.name }) { bike ->
            BikeInListElement(
                bikeModelName = bike.name,
                modifier = Modifier.animateItemPlacement(
                    animationSpec = tween(
                        durationMillis = 500  // TODO: Extract const
                    )
                )
            )
        }
    }
}