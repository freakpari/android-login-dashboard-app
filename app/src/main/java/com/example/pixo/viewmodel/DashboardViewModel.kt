package com.example.pixo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixo.data.TokenStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val tokenStore: TokenStore
) : ViewModel() {

    private val _shouldNavigateToAuth = MutableStateFlow(false)
    val shouldNavigateToAuth: StateFlow<Boolean> = _shouldNavigateToAuth.asStateFlow()

    fun logout() {
        viewModelScope.launch {
            tokenStore.clearRefreshToken()
            _shouldNavigateToAuth.value = true
        }
    }

    fun resetNavigation() {
        _shouldNavigateToAuth.value = false
    }
}