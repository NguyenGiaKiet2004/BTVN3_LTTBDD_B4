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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Homework() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
                containerColor = Color(0xFFFFC107),
                contentColor = Color.White,
                shape = RoundedCornerShape(50),
                modifier = Modifier.offset(y = 50.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 6.dp,
                modifier = Modifier
                    .height(80.dp)
                    .padding(horizontal = 8.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* TODO */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO */ },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )

                // Giữa để chừa khoảng trống cho FAB
                Spacer(modifier = Modifier.weight(1f))

                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO */ },
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
                    label = { Text("Calendar") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO */ },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 0.dp, // ❗ Không padding top để TopCard dính sát mép trên
                    start = 16.dp,
                    end = 16.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            TitleHomeWork()
            BodyHomeWork()
        }
    }
}

@Composable
fun TitleHomeWork(){
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
            text = "List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(parseColor("#2196F3")),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BodyHomeWork() {
    val itemCount =7
    val boxColors = listOf(
        Color(0xFFFFCDD2), // hồng nhạt
        Color(0xFFBBDEFB), // xanh dương nhạt
        Color(0xFFC8E6C9)  // xanh lá nhạt
    )
    LazyColumn{
        items(itemCount){index->
            Box(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        boxColors[index % boxColors.size], // lặp lại nếu index vượt quá danh sách
                    ),
                ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Task $index", fontWeight = FontWeight.Bold)
                    Text(text = "Description $index")
                }
            }
        }
    }
}



