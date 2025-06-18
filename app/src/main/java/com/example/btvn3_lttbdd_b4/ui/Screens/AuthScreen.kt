package com.example.btvn3_lttbdd_b4.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AuthScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("LoginScreen") }) {
            Text("Đăng nhập")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("RegisterScreen") }) {
            Text("Đăng ký")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("ForgotPasswordScreen") }) {
            Text("Quên mật khẩu")
        }
    }
}
