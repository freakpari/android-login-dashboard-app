package com.example.pixo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixo.data.TokenStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val tokenStore: TokenStore
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _shouldNavigateToDashboard = MutableStateFlow(false)
    val shouldNavigateToDashboard: StateFlow<Boolean> = _shouldNavigateToDashboard.asStateFlow()

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        if (_username.value == "user" && _password.value == "password") {
            viewModelScope.launch {
                tokenStore.saveRefreshToken("refresh_token_123")
                _shouldNavigateToDashboard.value = true
            }
        } else {
            _username.value = ""
            _password.value = ""
        }
    }

    fun resetNavigation() {
        _shouldNavigateToDashboard.value = false
    }

}