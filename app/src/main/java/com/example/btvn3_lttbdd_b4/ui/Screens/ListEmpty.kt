package com.example.btvn3_lttbdd_b4.ui.Screens

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.btvn3_lttbdd_b4.R
import com.example.btvn3_lttbdd_b4.data.model.Task
import com.example.btvn3_lttbdd_b4.ui.viewmodel.TaskViewModel

@Composable
fun ListEmpty(viewModel: TaskViewModel, navController: NavController) {
    val tasks = viewModel.tasks.collectAsState()
    val selectedTaskIds = viewModel.selectedTaskIds
    val selectedTasks = tasks.value.filter { it.id in selectedTaskIds }

    Column(
        modifier = Modifier.fillMaxSize(),
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
                text = "List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(parseColor("#2196F3")),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if(selectedTasks.isNotEmpty()){
            BodyTodoList(tasks = selectedTasks, viewModel = viewModel, navController = navController)
        }else{
            Body()
        }

    }
}
@Composable

fun Body() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(100.dp)
        )
        Text(
            text = "No Tasks Yet!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Stay productive - add something to do",
            fontSize = 16.sp,
        )
    }
}


@Composable
fun BodyTodoList(tasks: List<Task>, viewModel: TaskViewModel, navController: NavController) {
    Log.d("BodyTodoList", "Tasks size: ${tasks.size}")



//    val checkedState = remember { mutableStateListOf(*Array(tasks.size) { tasks.getOrNull(it)?.status == "Completed" }) }
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
                    .clickable {
                        if (task.id != null) {
                        navController.navigate("TaskDetail/${task.id}")
                        } else {
                            Log.e("NAVIGATION", "Task ID is null, cannot navigate.")
                        }
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

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