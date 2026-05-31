package com.example.pixo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pixo.R
import com.example.pixo.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    onNavigateToDashboard: () -> Unit,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit,
    onLanguageToggle: () -> Unit
) {
    val username by authViewModel.username.collectAsStateWithLifecycle()
    val password by authViewModel.password.collectAsStateWithLifecycle()
    val shouldNavigate by authViewModel.shouldNavigateToDashboard.collectAsStateWithLifecycle()

    val iconColor = if (isDarkTheme) Color.White else Color(0xD21F1D1D)
    val backgroundColor = if (isDarkTheme) Color(0xFF121212) else Color(0xFFF9F9F9)
    val buttonColor = if (isDarkTheme) Color(0xFFE0E0E0) else Color(0xFF333333)
    val buttonTextColor = if (isDarkTheme) Color.Black else Color.White
    val labelColor = if (isDarkTheme) Color.LightGray else Color(0xFF757575)

    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            authViewModel.resetNavigation()
            onNavigateToDashboard()
        }
    }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Row(modifier = Modifier.padding(start = 12.dp)) {
                        IconButton(onClick = onLanguageToggle) {
                            Icon(
                                painter = painterResource(R.drawable.ic_language),
                                contentDescription = "Language",
                                modifier = Modifier.size(28.dp),
                                tint = iconColor
                            )
                        }
                        IconButton(onClick = onThemeToggle) {
                            Icon(
                                painter = painterResource(
                                    if (isDarkTheme) R.drawable.ic_sun else R.drawable.ic_moon
                                ),
                                contentDescription = "Theme",
                                modifier = Modifier.size(28.dp),
                                tint = iconColor
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_lock),
                contentDescription = "Lock",
                modifier = Modifier.size(80.dp),
                tint = iconColor
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = stringResource(R.string.login_title),
                fontSize = 20.sp,
                color = labelColor
            )

            Text(
                text = stringResource(R.string.username_hint),
                fontSize = 13.sp,
                color = labelColor.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { authViewModel.updateUsername(it) },
                label = { Text(stringResource(R.string.username_label), fontSize = 12.sp) },
                placeholder = { Text(stringResource(R.string.username_placeholder), fontSize = 14.sp) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = iconColor,
                    unfocusedLabelColor = labelColor,
                    focusedLabelColor = iconColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { authViewModel.updatePassword(it) },
                label = { Text(stringResource(R.string.password_label), fontSize = 12.sp) },
                placeholder = { Text(stringResource(R.string.password_placeholder), fontSize = 14.sp) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = iconColor,
                    unfocusedLabelColor = labelColor,
                    focusedLabelColor = iconColor
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { authViewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(
                    text = stringResource(R.string.submit_button),
                    fontSize = 16.sp,
                    color = buttonTextColor
                )
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}