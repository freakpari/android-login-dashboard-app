package com.example.pixo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pixo.view.DashboardContent
import com.example.pixo.viewmodel.DashboardViewModel
import com.example.pixo.viewmodel.IconViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    iconViewModel: IconViewModel = viewModel(),
    dashboardViewModel: DashboardViewModel,
    onNavigateToAuth: () -> Unit
) {
    val shouldNavigate by dashboardViewModel.shouldNavigateToAuth.collectAsStateWithLifecycle()

    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            dashboardViewModel.resetNavigation()
            onNavigateToAuth()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DashboardContent(iconViewModel)

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { dashboardViewModel.logout() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Logout")
            }
        }
    }
}