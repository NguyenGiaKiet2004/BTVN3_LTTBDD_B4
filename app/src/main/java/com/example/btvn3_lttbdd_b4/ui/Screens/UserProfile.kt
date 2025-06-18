package com.example.btvn3_lttbdd_b4.ui.Screens

import android.graphics.Color.parseColor
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth

@Composable
fun UserProfile(email: String) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            // Nút back ở bên trái
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color(parseColor("#2196F3")))
                    .padding(0.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                )
            )
            {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            Text(
                text = "Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorScheme.onSurface,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        AvatarPicker()
        Spacer(modifier = Modifier.height(16.dp))
        InformationPerson(email)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {FirebaseAuth.getInstance().signOut()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(parseColor("#0099FF")),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(34.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 40.dp)
                .height(60.dp)
        ) {
            Text(text = "Đăng xuất", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun AvatarPicker() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .clickable { launcher.launch("image/*") }
            .background(Color.Gray),
        contentAlignment = Alignment.Center,
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Default Avatar",
                modifier = Modifier.size(100.dp),
                tint = Color.White
            )
        }
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Default Avatar",
            modifier = Modifier.size(40.dp).align(Alignment.BottomCenter),
            tint = Color.Black,

        )
    }
}

@Composable
fun InformationPerson(email:String){
    // Nội dung bên dưới
    var Name by remember { mutableStateOf("") }
    var Email by remember { mutableStateOf("") }
    var Date by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(350.dp)
            .background(
                color = colorScheme.surface,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 15.dp, vertical = 30.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Name",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        OutlinedTextField(
            value = Name,
            onValueChange = {Name=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            shape = RoundedCornerShape(12.dp),
        )
        Text(
            text = "Email",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        OutlinedTextField(
            value = email,
            onValueChange = {Email=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            shape = RoundedCornerShape(12.dp),
        )
        Text(
            text = "Date of Birthday",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        OutlinedTextField(
            value = Date,
            onValueChange = {Date=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            shape = RoundedCornerShape(12.dp),
        )

    }
}



