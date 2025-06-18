// File: app/src/main/java/com/your_app_name/repository/TaskRepository.kt

package com.example.btvn3_lttbdd_b4.repository

import com.example.btvn3_lttbdd_b4.data.api.RetrofitInstance

class UserRepository {
    suspend fun getUsers() = RetrofitInstance.api.getUsers()

}

