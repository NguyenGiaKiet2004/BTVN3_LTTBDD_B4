package com.example.btvn3_lttbdd_b4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var email by mutableStateOf("")
    var OTPs by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
}