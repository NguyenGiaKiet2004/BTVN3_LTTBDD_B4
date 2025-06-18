package com.example.btvn1_lttbdd_b4.ui.Screens


import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import com.example.btvn3_lttbdd_b4.R

@Composable
fun ForgotPassword2(currentPage: Int, totalPages: Int, openPage3:(String)->Unit, BackPage1:()->Unit) {
//quan trong:danh sach============================================================
//danh sach co the chinhsua so luong
    //mutableStateListOf("", "", "", "", "")
    //truy cap bang soList[0]
    val soList = remember {
        List(5) { mutableStateOf("") }
    }

    // Phần nội dung căn giữa

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Nút back ở bên trái
            IconButton(
                onClick = {BackPage1()},
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
        }

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(vertical = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //quan trong===================================================
            //repeat() vaf index-> nhu ham for vowis chir so tuowng ung laf i tang dan
            repeat(5) {index->
                OutlinedTextField(
                    value = soList[index].value,//lay gia tri
                    onValueChange = { input ->
                        // Chỉ cho phép nhập 1 ký tự và phải là số (0-9)
                        if (input.length <= 1 && input.all { it.isDigit() }) {
                            soList[index].value = input//thay doi gia tri },
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    //singleLine = true,
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),

                )
            }

        }
//hợp các giá trị trong danh sách thành 1 chuỗi
val OTP = soList.joinToString("") { it.value }
        Button(
            onClick = {
                openPage3(OTP)},
            modifier = Modifier
                .padding(top = 20.dp)
                .height(60.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(parseColor("#0099FF"))
            )

        ) {
            Text(
                text = "Next",
                fontSize = 24.sp)
        }

    }
}

