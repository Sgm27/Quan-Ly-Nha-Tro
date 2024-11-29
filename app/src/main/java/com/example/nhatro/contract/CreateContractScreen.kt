package com.example.nhatro.userinfo

import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateContractScreen(navController: NavController) {
    Scaffold(
        topBar = { CreateContractTopBar(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

            }
        }
    )
}




@Composable
fun CreateContractTopBar(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color(0xFFF5F5F5) // Màu nền trắng đục
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút Back
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .border(1.dp, Color.Gray, shape = CircleShape) // Viền xung quanh nút
                    .background(Color.White, shape = CircleShape) // Nền trắng cho nút
                    .clickable { navController.popBackStack() }, // Quay lại màn hình trước
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black // Màu icon là đen
                )
            }

            Spacer(modifier = Modifier.width(12.dp)) // Khoảng cách giữa nút Back và tiêu đề

            // Tiêu đề và phụ đề
            Column {
                Text(
                    text = "Lập hợp đồng mới", // Tiêu đề sửa lại theo yêu cầu
                    color = Color.Black, // Màu chữ đen
                    fontSize = 18.sp, // Kích thước chữ tiêu đề
                    fontWeight = FontWeight.Bold // Đậm cho tiêu đề
                )
                Text(
                    text = "Phòng 1 - #104160", // Phụ đề
                    color = Color.Gray, // Màu chữ xám
                    fontSize = 14.sp, // Kích thước chữ phụ đề nhỏ hơn
                    fontWeight = FontWeight.Medium // Đậm nhẹ cho phụ đề
                )
            }
        }
    }
}
