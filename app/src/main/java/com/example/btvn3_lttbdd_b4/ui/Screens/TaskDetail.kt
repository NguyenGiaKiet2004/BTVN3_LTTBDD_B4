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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.btvn3_lttbdd_b4.data.model.Task
import com.example.btvn3_lttbdd_b4.ui.viewmodel.TaskViewModel

@Composable
fun TaskDetail(TaskId : Int,navController: NavController,viewModel: TaskViewModel) {

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
                IconButton(onClick = {
                    navController.navigate("ListEmpty")
                }) { }
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

            // Nút xoa ở bên phai
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color(parseColor("#FF6600")))
                    .padding(0.dp)
                    .align(Alignment.CenterEnd),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                )
            )
            {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        BodyDetail(TaskId = TaskId, viewModel = viewModel)
    }
}
@Composable

fun BodyDetail(TaskId :Int ,viewModel: TaskViewModel ) {
    val task = viewModel.getTaskById(TaskId)
    if (task == null) {
        Text("Task không tồn tại hoặc đã bị xóa", color = Color.Red)
        return
    }
    val listSubTask = task.subtasks

    // Khởi tạo index2 cho ngoai LazyColumn
    var index2 = 0

    var checkState = remember { mutableStateListOf(*Array(listSubTask.size) { false }) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(
            text= task.title ,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,

        )
        Text(
            text= task.description,
            fontSize = 15.sp,

        )
        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(parseColor("#FFCCCC"))),
            verticalAlignment = Alignment.CenterVertically

        ){
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // DANH SÁCH CỤM

                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date",
                        modifier = Modifier.size(24.dp)
                    )
                    Column {
                        Text(text = "Category")
                        Text(text = task.category, fontWeight = FontWeight.Bold)
                    }
                }

                // DANH SÁCH CỤM

                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date",
                        modifier = Modifier.size(24.dp)
                    )
                    Column {
                        Text(text = "Status")
                        Text(text = task.status, fontWeight = FontWeight.Bold)
                    }
                }

                // DANH SÁCH CỤM

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date",
                        modifier = Modifier.size(24.dp)
                    )
                    Column {
                        Text(text = "Priority")
                        Text(text = task.priority, fontWeight = FontWeight.Bold)
                    }
                }

            }
        }

        Text(
            text="Subtasks",
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
        ){
            LazyColumn{

                items(listSubTask!!.size){index->
                    index2 = index
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Color(parseColor("#EEEEEE"))
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checkState[index],
                            onCheckedChange = {checkState[index]=it},
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black,
                                uncheckedColor = Color.Black,
                                disabledCheckedColor = Color.Gray
                            )
                        )
                        Text(text = listSubTask[index].title)
                    }
                }

            }
        }

        Text(
            text="Attachments",
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Color(parseColor("#EEEEEE"))
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Date",
                modifier = Modifier.size(30.dp)
            )
            Text(text = listSubTask[index2].title)
        }

    }
}


