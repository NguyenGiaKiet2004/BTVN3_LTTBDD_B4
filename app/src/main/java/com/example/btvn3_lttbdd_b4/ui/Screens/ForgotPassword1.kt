package com.example.btvn1_lttbdd_b4.ui.Screens


import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.btvn3_lttbdd_b4.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgotPassword1(currentPage: Int, totalPages: Int, openPage2:(String)->Unit,navController: NavController) {
    val context = LocalContext.current
    var email by remember {mutableStateOf("")}

        // Phần nội dung căn giữa

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top=100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(id = R.drawable.uth),
                contentDescription = "hinh anh",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text="SmartTasks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 10.dp),
                color = Color(parseColor("#0099FF"))
            )

            Text(
                text = "Forget Password?",
                modifier = Modifier.padding(20.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Enter your Email, we will send you a verification code",
                color = Color.Gray,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text="Email") },
                placeholder = { Text(text = "Enter your email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(parseColor("#0099FF")),
                    unfocusedBorderColor = Color.LightGray,

                    focusedTextColor = Color(parseColor("#0099FF")),
                    unfocusedTextColor = Color.Black,

                    focusedLeadingIconColor = Color(parseColor("#0099FF")),
                    unfocusedLeadingIconColor = Color.Gray
                ),
                maxLines = 1,
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Icon o dau") }
            )

            Button(
                onClick = {
                    openPage2(email)
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Email đặt lại mật khẩu đã được gửi", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Lỗi: ${it.message}", Toast.LENGTH_SHORT).show()
                        }

                          },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(parseColor("#0099FF"))
                )

            ) {
                Text(text = "Next")
            }

        }
    }

