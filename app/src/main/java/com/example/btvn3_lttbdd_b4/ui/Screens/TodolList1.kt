package com.example.btvn3_lttbdd_b4.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.btvn3_lttbdd_b4.R
import com.example.btvn3_lttbdd_b4.data.model.Task
import com.example.btvn3_lttbdd_b4.ui.viewmodel.TaskViewModel

@Composable
fun TodoList1(viewModel: TaskViewModel = viewModel(), navController: NavController) {
    val tasks = viewModel.tasks.collectAsState()
    val error = viewModel.error.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Thêm task */ },
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
                modifier = Modifier.height(80.dp).padding(horizontal = 16.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* TODO: Home */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO: Search */ },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )
                Spacer(modifier = Modifier.weight(1f))
                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO: Calendar */ },
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
                    label = { Text("Calendar") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* TODO: Settings */ },
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
                    top = 0.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            TopCard()
            Spacer(modifier = Modifier.height(10.dp))
            error.value?.let {
                Text(
                    text = "Error: $it",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
            BodyTodoList(tasks = tasks.value, viewModel = viewModel, navController = navController)
        }
    }
}

@Composable
fun TopCard() {
    val context = LocalContext.current
    val isResourceAvailable = try {
        context.resources.getIdentifier("uth", "drawable", context.packageName) != 0
    } catch (e: Exception) {
        false
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isResourceAvailable) {
                Image(
                    painter = painterResource(id = R.drawable.uth),
                    contentDescription = "Logo",
                    modifier = Modifier.height(32.dp)
                )
            } else {
                Text(text = "Logo not found", color = Color.Red)
            }
            Column(
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "SmartTasks",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF0099FF)
                )
                Text(
                    text = "A simple and efficient to-do app",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(
                onClick = { /* TODO: Notifications */ },
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color(0xFFFF6600))
                    .padding(0.dp),
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun BodyTodoList(tasks: List<Task>, viewModel: TaskViewModel, navController: NavController) {
    Log.d("BodyTodoList", "Tasks size: ${tasks.size}")
    if (tasks.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No tasks available",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Button(onClick = { viewModel.fetchTasks() }) {
                Text("Retry")
            }
        }
        return
    }

    val checkedState = remember { mutableStateListOf(*Array(tasks.size) { tasks.getOrNull(it)?.status == "Completed" }) }
    val boxColors = listOf(Color(0xFFFFCDD2), Color(0xFFBBDEFB), Color(0xFFC8E6C9))

    LazyColumn {
        items(tasks.size) { index ->
            val task = tasks[index]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(boxColors[index % boxColors.size])
                    .clickable { navController.navigate("taskDetail/${task.id}") }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedState[index],
                            onCheckedChange = { checkedState[index] = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black,
                                uncheckedColor = Color.Black,
                                disabledCheckedColor = Color.Gray
                            )
                        )
                        Column {
                            Text(text = "Task ${task.id}: ${task.title}", fontWeight = FontWeight.Bold)
                            Text(text = task.description)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Status: ${task.status}")
                        Text(text = task.dueDate)
                    }
                }
            }
        }
    }
}