package com.example.btvn3_lttbdd_b4

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.btvn1_lttbdd_b4.Screens.Page1
import com.example.btvn1_lttbdd_b4.Screens.Page2
import com.example.btvn1_lttbdd_b4.Screens.Page3
import com.example.btvn1_lttbdd_b4.Screens.Page4
import com.example.btvn3_lttbdd_b4.ui.theme.BTVN3_LTTBDD_B4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVN3_LTTBDD_B4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)  // 👈 Sử dụng innerPadding ở đây
                            .fillMaxSize()
                    ) {
                        ManageScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun ManageScreen(){
    val navController = rememberNavController()
    var viewModel : MainViewModel = viewModel()
    BTVN3_LTTBDD_B4Theme{
        NavHost(navController= navController, startDestination = "Page1"){
            composable ("Page1") {
                Page1(1,3,
                    openPage2 = {
                        email->
                        viewModel.email = email
                        navController.navigate("Page2")
                    },
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
            composable("Page2") {
                Page2(1,3,
                    openPage3 = { OTP->
                        viewModel.OTPs =OTP
                        navController.navigate("Page3")
                    },
                    BackPage1 = {
                        navController.navigate("Page1")
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
            composable ("Page3") {
                Page3(1,3,
                    openPage4 = {password->
                        viewModel.password = password
                        navController.navigate("Page4")
                    },
                    BackPage2 = {
                        navController.navigate("Page2")
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

            composable("Page4") {
                Page4(
                    1, 3,
                    email = viewModel.email,
                    password = viewModel.password,
                    OTPs = viewModel.OTPs,
                    submit = {
                        navController.navigate("Page1")
                    },
                    BackPage3 = {
                        navController.navigate("Page3")
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