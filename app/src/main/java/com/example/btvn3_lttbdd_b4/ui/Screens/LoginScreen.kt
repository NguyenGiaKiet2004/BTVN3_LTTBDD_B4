package com.example.btvn1_lttbdd_b4.ui.Screens

import android.graphics.Color.parseColor
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.btvn3_lttbdd_b4.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(openPage1: (String) -> Unit, navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        navController.navigate("UserProfile/${account.email}")
                    } else {
                        Toast.makeText(context, "Đăng nhập Google thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 80.dp)
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
            text = "SmartTasks",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 10.dp),
            color = Color(parseColor("#0099FF"))
        )

        Text(
            text = "A simple and efficient to-do app",
            color = Color.Gray,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ======= Nhập email =======
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ======= Nhập password =======
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ======= Nút đăng nhập bằng email =======
        Button(
            onClick = {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.trim(), password.trim())
                    .addOnSuccessListener {
                        openPage1(email.trim())
                        navController.navigate("UserProfile/${Uri.encode(email.trim())}")
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Lỗi: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Đăng nhập bằng Email")
        }

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Welcome",
            color = Color.DarkGray,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Ready to explore? Login to get started",
            color = Color.Gray,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        // ======= Nút đăng nhập bằng Google =======
        Button(
            onClick = { launcher.launch(googleSignInClient.signInIntent) },
            modifier = Modifier
                .padding(top = 20.dp)
                .height(60.dp)
                .width(300.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(parseColor("#99FFFF"))
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google logo",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "SIGN IN WITH GOOGLE",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(parseColor("#330066"))
                )
            }
        }
    }
}
