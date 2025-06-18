package com.example.btvn3_lttbdd_b4.ui.Screens

import UserViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val users = viewModel.users.collectAsState()

    LazyColumn {
        items(users.value) { user ->
            Text(text = "${user.id} - ${user.name}")
        }
    }
}
