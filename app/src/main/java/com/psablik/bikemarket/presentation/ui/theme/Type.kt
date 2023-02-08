package com.psablik.bikemarket.presentation.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psablik.bikemarket.R

val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
)

val H1 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontSize = 40.sp,
    lineHeight = 56.sp
)

val H2 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontSize = 32.sp,
    lineHeight = 40.sp
)

val H3 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontSize = 22.sp,
    lineHeight = 32.sp
)

val H4 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontSize = 20.sp,
    lineHeight = 32.sp
)

val B1 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontSize = 18.sp,
    lineHeight = 32.sp
)

val B2 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
    fontSize = 18.sp,
    lineHeight = 32.sp
)

val B3 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
    fontSize = 16.sp,
    lineHeight = 20.sp
)

val B4 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontSize = 16.sp,
    lineHeight = 32.sp
)

val B5 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_light)),
    fontSize = 14.sp,
    lineHeight = 24.sp
)

val Info = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontSize = 12.sp,
    lineHeight = 16.sp
)

val Info2 = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
    fontSize = 12.sp,
    lineHeight = 16.sp
)

val SmallInfo = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontSize = 10.sp,
    lineHeight = 16.sp
)

// Function added to preview text styles
@Composable
@Preview(
    showBackground = true,
    device = Devices.PIXEL_2_XL
)
fun TextStylesPreview() {
    Column {
        Row {
            TextColumn(text = "H1 text", style = H1, modifier = Modifier.weight(1f))
            TextColumn(text = "H2 text", style = H2, modifier = Modifier.weight(1f))
            TextColumn(text = "H3 text", style = H3, modifier = Modifier.weight(1f))
        }
        Row {
            TextColumn(text = "H4 text", style = H4, modifier = Modifier.weight(1f))
            TextColumn(text = "B1 text", style = B1, modifier = Modifier.weight(1f))
            TextColumn(text = "B2 text", style = B2, modifier = Modifier.weight(1f))
        }
        Row {
            TextColumn(text = "B3 text", style = B3, modifier = Modifier.weight(1f))
            TextColumn(text = "B4 text", style = B4, modifier = Modifier.weight(1f))
            TextColumn(text = "B5 text", style = B5, modifier = Modifier.weight(1f))
        }
        Row {
            TextColumn(text = "Info text", style = Info, modifier = Modifier.weight(1f))
            TextColumn(text = "Info2 text", style = Info2, modifier = Modifier.weight(1f))
            TextColumn(text = "SmallInfo text", style = SmallInfo, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TextColumn(
    modifier: Modifier = Modifier,
    text: String = "Some text",
    style: TextStyle = TextStyle.Default
) {
    Column(
        modifier
            .border(BorderStroke(MaterialTheme.spacing.l, Color.Black))
            .padding(5.dp)
    ) {
        Text(text = text, style = style)
    }
}