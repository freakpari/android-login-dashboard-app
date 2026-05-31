package com.example.pixo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pixo.data.TokenStore
import com.example.pixo.navigation.Screen
import com.example.pixo.ui.screens.AuthScreen
import com.example.pixo.ui.screens.DashboardScreen
import com.example.pixo.utils.LocaleManager
import com.example.pixo.viewmodel.AuthViewModel
import com.example.pixo.viewmodel.DashboardViewModel
import com.example.pixo.viewmodel.IconViewModel

class MainActivity : ComponentActivity() {

    private lateinit var tokenStore: TokenStore

    override fun attachBaseContext(newBase: Context) {
        val lang = LocaleManager.getSavedLanguage(newBase)
        super.attachBaseContext(LocaleManager.setLocale(newBase, lang))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenStore = TokenStore(applicationContext)

        setContent {
            val context = LocalContext.current
            val prefs = remember { context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }

            var isDarkTheme by remember {
                mutableStateOf(prefs.getBoolean("is_dark_mode", false))
            }

            var currentLanguage by remember {
                mutableStateOf(prefs.getString("app_language", "en") ?: "en")
            }

            val colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()

            MaterialTheme(colorScheme = colorScheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    val authViewModel: AuthViewModel = viewModel(
                        factory = viewModelFactory {
                            addInitializer(AuthViewModel::class) { AuthViewModel(tokenStore) }
                        }
                    )

                    val dashboardViewModel: DashboardViewModel = viewModel(
                        factory = viewModelFactory {
                            addInitializer(DashboardViewModel::class) { DashboardViewModel(tokenStore) }
                        }
                    )

                    val iconViewModel: IconViewModel = viewModel()

                    NavHost(navController = navController, startDestination = Screen.Auth.route) {
                        composable(Screen.Auth.route) {
                            AuthScreen(
                                authViewModel = authViewModel,
                                onNavigateToDashboard = {
                                    navController.navigate(Screen.Dashboard.route) {
                                        popUpTo(Screen.Auth.route) { inclusive = true }
                                    }
                                },
                                isDarkTheme = isDarkTheme,
                                onThemeToggle = {
                                    val newValue = !isDarkTheme
                                    isDarkTheme = newValue
                                    prefs.edit().putBoolean("is_dark_mode", newValue).apply()
                                },
                                onLanguageToggle = {
                                    val newLanguage = if (currentLanguage == "en") "fa" else "en"
                                    prefs.edit().putString("app_language", newLanguage).apply()
                                    finish()
                                    startActivity(intent)
                                }
                            )
                        }
                        composable(Screen.Dashboard.route) {
                            DashboardScreen(
                                iconViewModel = iconViewModel,
                                dashboardViewModel = dashboardViewModel,
                                onNavigateToAuth = {
                                    navController.navigate(Screen.Auth.route) {
                                        popUpTo(Screen.Dashboard.route) { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}