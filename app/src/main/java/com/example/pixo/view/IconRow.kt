package com.example.pixo.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pixo.R
import com.example.pixo.viewmodel.IconType
import com.example.pixo.viewmodel.IconViewModel

@Composable
fun IconRow(viewModel: IconViewModel) {

    val selected = viewModel.selectedIcon

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        IconItem(
            icon = R.drawable.ic_car,
            isSelected = selected == IconType.CAR
        ) { viewModel.onIconSelected(IconType.CAR) }

        IconItem(
            icon = R.drawable.ic_cat,
            isSelected = selected == IconType.CAT
        ) { viewModel.onIconSelected(IconType.CAT) }

        IconItem(
            icon = R.drawable.ic_fish,
            isSelected = selected == IconType.FISH
        ) { viewModel.onIconSelected(IconType.FISH) }
    }
}

@Composable
fun IconItem(
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)
                .alpha(if (isSelected) 1f else 0.3f)
        )
    }
}