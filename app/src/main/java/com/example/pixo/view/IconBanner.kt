package com.example.pixo.view

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.res.colorResource
import com.example.pixo.R
import com.example.pixo.viewmodel.IconType
import com.example.pixo.viewmodel.IconViewModel

@Composable
fun IconBanner(viewModel: IconViewModel) {
    val animalText = when (viewModel.selectedIcon) {
        IconType.CAR -> "Car"
        IconType.CAT -> "Cat"
        IconType.FISH -> "Fish"
    }

    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.dark_gray)
        )
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = colorResource(R.color.light_gray))) {
                    append("You want a ")
                }
                withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                    append(animalText)
                }
            },
            fontSize = 16.sp
        )
    }
}