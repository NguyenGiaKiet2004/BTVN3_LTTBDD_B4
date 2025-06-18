package com.example.btvn3_lttbdd_b4

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel// Import helper để lấy ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.btvn1_lttbdd_b4.ui.Screens.ForgotPassword1
import com.example.btvn1_lttbdd_b4.ui.Screens.ForgotPassword2
import com.example.btvn1_lttbdd_b4.ui.Screens.ForgotPassword3
import com.example.btvn1_lttbdd_b4.ui.Screens.ForgotPassword4
import com.example.btvn1_lttbdd_b4.ui.Screens.LoginScreen

import com.example.btvn3_lttbdd_b4.ui.Screens.AddNewTask
import com.example.btvn3_lttbdd_b4.ui.Screens.AuthScreen
import com.example.btvn3_lttbdd_b4.ui.Screens.Homework
import com.example.btvn3_lttbdd_b4.ui.Screens.ListEmpty
import com.example.btvn3_lttbdd_b4.ui.Screens.RegisterScreen
import com.example.btvn3_lttbdd_b4.ui.Screens.TaskDetail
import com.example.btvn3_lttbdd_b4.ui.Screens.TodoList
import com.example.btvn3_lttbdd_b4.ui.Screens.UserProfile
import com.example.btvn3_lttbdd_b4.ui.Screens.UserScreen
import com.example.btvn3_lttbdd_b4.ui.Screens.TodoList1
import com.example.btvn3_lttbdd_b4.ui.theme.BTVN3_LTTBDD_B4Theme
import com.example.btvn3_lttbdd_b4.ui.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVN3_LTTBDD_B4Theme {
                // Khởi tạo Repository và Factory
                ManageScreen()
            }
        }
    }
}

@Composable
fun ManageScreen(){
    val navController = rememberNavController()
    var viewModel : MainViewModel = viewModel()
    val taskViewModel: TaskViewModel = viewModel()


    BTVN3_LTTBDD_B4Theme{

        NavHost(navController= navController, startDestination = "TodoList1"){
            composable("TaskScreen") {
                UserScreen()
            }
            composable("AddNewTask") {
                AddNewTask()
            }
            composable("Homework") {
                Homework(
                )
            }
            composable(
                "TaskDetail/{taskId}",
                arguments = listOf(navArgument("taskId") {
                    type = NavType.IntType // hoặc NavType.StringType tùy kiểu của task.id
                })
            ) {
                backStackEntry ->
                val taskId = backStackEntry.arguments?.getInt("taskId")
                if (taskId != null) {
                    TaskDetail(TaskId = taskId,navController=navController,viewModel = taskViewModel) // ok vì taskId đã là Int
                } else {
                    // Có thể chuyển sang màn hình lỗi, hoặc Toast, hoặc Log
                    Text("Không tìm thấy ID của Task")
                }

            }

            composable("TodoList1") {
                TodoList1(viewModel = taskViewModel,navController = navController)
            }
            composable("ListEmpty") {
                ListEmpty(viewModel = taskViewModel,navController= navController)
            }
            composable("TodoList") {
                TodoList()
            }

            composable("AuthScreen") {
                AuthScreen(
                    navController = navController
                )
            }

            composable("LoginScreen") {
                LoginScreen(
                    openPage1 = { email->
                        viewModel.email = email
                        navController.navigate("User-Profile")
                    }
                    ,navController = navController
                )
            }

            composable("RegisterScreen") {
                RegisterScreen(navController)
            }

            // Định nghĩa trang UserProfile
            composable("UserProfile/{email}") { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email") ?: ""
                UserProfile(email = Uri.decode(email))
            }


            composable ("ForgotPassword1") {
                ForgotPassword1(1,3,
                    openPage2 = {
                        email->
                        viewModel.email = email
                        navController.navigate("ForgotPassword2")
                    },
                    navController = navController
                )
            }
            /*composable ("Page1") {
                Page1(1,3,
                    openPage2 = {
                        email->
                        navController.navigate("Page4/$email")
                        navController.navigate("Page2")
                    },
                )
            }*/
            composable("ForgotPassword2") {
                ForgotPassword2(1,3,
                    openPage3 = { OTP->
                        viewModel.OTPs =OTP
                        navController.navigate("ForgotPassword3")
                    },
                    BackPage1 = {
                        navController.navigate("ForgotPassword1")
                    },

                )
            }
            //Dùng Navigative
            /*composable("Page2") {
                Page2(1,3,
                    openPage3 = { OTP1,OTP2,OTP3,OTP4,OTP5->
                        navController.navigate("Page4/$OTP1/$OTP2/$OTP3/$OTP4/$OTP5")
                        navController.navigate("Page3")
                    },
                    BackPage1 = {
                        navController.navigate("Page1")
                    },

                )
            }*/
            composable ("ForgotPassword3") {
                ForgotPassword3(1,3,
                    openPage4 = {password->
                        viewModel.password = password
                        navController.navigate("ForgotPassword4")
                    },
                    BackPage2 = {
                        navController.navigate("ForgotPassword2")
                    }
                )
            }
            /*composable ("Page3") {
                Page3(1,3,
                    openPage4 = {password->
                        navController.navigate("Page4/$password")
                        navController.navigate("Page4")
                    },
                    BackPage2 = {
                        navController.navigate("Page2")
                    }
                )
            }*/

            composable("ForgotPassword4") {
                ForgotPassword4(
                    1, 3,
                    email = viewModel.email,
                    password = viewModel.password,
                    OTPs = viewModel.OTPs,
                    submit = {
                        navController.navigate("ForgotPassword1")
                    },
                    BackPage3 = {
                        navController.navigate("ForgotPassword3")
                    }
                )
            }
            //Cách dùng Navigative
           /* composable ( route ="Page4/{email}/{password}/{confirmPassword}/{OTP1}/{OTP2}/{OTP3}/{OTP4}/{OTP5}",
                arguments = listOf(
                    navArgument("email"){
                        type = NavType.StringType
                    },
                    navArgument("password"){
                        type = NavType.StringType
                    },
                    navArgument("confirmPassword"){
                        type = NavType.StringType
                    }
                )

            ) {
                backStackEntry ->
                val email = backStackEntry.arguments?.getString("email") ?: ""
                val password = backStackEntry.arguments?.getString("password") ?: ""
                val confirmPassword = backStackEntry.arguments?.getString("confirmPassword") ?: ""
                val OTP1 = backStackEntry.arguments?.getString("OTP1") ?: ""
                val OTP2 = backStackEntry.arguments?.getString("OTP2") ?: ""
                val OTP3 = backStackEntry.arguments?.getString("OTP3") ?: ""
                val OTP4 = backStackEntry.arguments?.getString("OTP4") ?: ""
                val OTPs= OTP1+OTP2+OTP3+OTP4
                Page4(1,3, email ?: "", password ?: "", confirmPassword ?: "",OTPs ?: ""
                    , submit = {
                        navController.navigate("Page1")
                    }
                    , BackPage3 = {
                        navController.navigate("Page3")
                    })
            }*/

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BTVN3_LTTBDD_B4Theme {

    }
}