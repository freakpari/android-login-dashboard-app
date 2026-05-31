package com.example.pixo.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

enum class IconType {
    CAR, CAT, FISH
}

class IconViewModel : ViewModel() {

    var selectedIcon by mutableStateOf(IconType.CAT)
        private set

    fun onIconSelected(icon: IconType) {
        selectedIcon = icon
    }
}