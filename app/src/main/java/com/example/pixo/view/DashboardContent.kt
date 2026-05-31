package com.example.pixo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pixo.R
import com.example.pixo.viewmodel.IconViewModel

@Composable
fun DashboardContent(viewModel: IconViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.name),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = stringResource(R.string.student_id_label) + " ",
                color = Color.Gray,
            )
            Text(
                text = stringResource(R.string.student_id_value),
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(
                text = stringResource(R.string.soroush_label) + " ",
                color = Color.Gray,
            )
            Text(
                text = stringResource(R.string.soroush_value),
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        IconRow(viewModel)

        Spacer(modifier = Modifier.height(16.dp))

        IconBanner(viewModel)
    }
}