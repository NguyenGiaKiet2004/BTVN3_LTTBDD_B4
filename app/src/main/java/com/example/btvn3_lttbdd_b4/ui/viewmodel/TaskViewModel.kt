// File: app/src/main/java/com/your_app_name/viewmodel/TaskViewModel.kt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.btvn3_lttbdd_b4.model.User
import com.example.btvn3_lttbdd_b4.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = repository.getUsers()
                _users.value = response
            } catch (e: Exception) {
                // Xử lý lỗi ở đây
            }
        }
    }
}
