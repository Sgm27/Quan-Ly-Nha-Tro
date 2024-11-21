package com.example.nhatro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddAssetScreen(navController: NavController) {
    Scaffold(
        topBar = { AddAssetTopBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Nội dung thêm tài sản",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun AddAssetTopBar(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50)), // Màu xanh lá
        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp), // Khoảng cách hai bên
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Back
            Icon(
                painter = painterResource(id = R.drawable.back_regis_icon_v2),
                contentDescription = "Back Icon",
                tint = Color.White, // Màu trắng cho icon
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() } // Quay lại khi nhấn vào
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Tiêu đề
            Column {
                Text(
                    text = "Thêm tài sản",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp
                )
                Text(
                    text = "Nhà trọ Đức Sơn",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 14.sp
                )
            }
        }
    }
}
