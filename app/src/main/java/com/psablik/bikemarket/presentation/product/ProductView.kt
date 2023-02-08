package com.psablik.bikemarket.presentation.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductView(
    productId: String
) {

    Column {
        Text(text = "Product - $productId")
    }

}