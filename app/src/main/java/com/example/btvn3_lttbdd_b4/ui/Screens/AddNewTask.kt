package com.example.btvn3_lttbdd_b4.ui.Screens


import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddNewTask() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 0.dp, // ❗ Không padding top để TopCard dính sát mép trên
                start = 16.dp,
                end = 16.dp,
            )
    ) {
        TitleAddNew()
        BodyAddNew()

    }
}




@Composable
fun TitleAddNew(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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
            text = "Add New",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(parseColor("#2196F3")),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BodyAddNew() {
    var taskName by remember{mutableStateOf("")}
    var taskDescription by remember{mutableStateOf("")}

    Text(text = "Task", fontWeight = FontWeight.Bold, fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    OutlinedTextField(
        value = taskName,
        onValueChange = {taskName=it},
        placeholder = { Text("Task") },
        modifier = Modifier
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(parseColor("#2196F3")),
            unfocusedBorderColor = Color.LightGray,
        )
    )
    Text(text = "Description", fontWeight = FontWeight.Bold, fontSize = 20.sp,
        modifier = Modifier.padding(vertical = 8.dp))
    OutlinedTextField(
        value = taskDescription,
        onValueChange = {taskDescription=it},
        placeholder = { Text("Description") },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(parseColor("#2196F3")),
            unfocusedBorderColor = Color.LightGray,
        )
    )

    Button(
        onClick = {},
        shape = RoundedCornerShape(24.dp),
        colors= ButtonDefaults.buttonColors(
            containerColor = Color(parseColor("#2196F3")),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp, vertical = 20.dp)
    ) {
        Text(text = "Add", fontSize = 24.sp)
    }
}