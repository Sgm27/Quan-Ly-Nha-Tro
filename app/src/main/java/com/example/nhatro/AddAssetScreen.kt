package com.example.nhatro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAssetScreen(navController: NavController) {
    Scaffold(
        topBar = { AddAssetTopBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Tên tài sản
            InputField(title = "Tên tài sản*", hint = "Ví dụ: Tủ lạnh")

            Spacer(modifier = Modifier.height(16.dp))

            // Chọn icon
            IconGridSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Nút Đóng và Thêm tài sản
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Xử lý đóng */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(text = "Đóng", color = Color.Black)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /* Xử lý thêm tài sản */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text(text = "+ Thêm tài sản", color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(title: String, hint: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") } // Quản lý giá trị nhập
    var isFocused by remember { mutableStateOf(false) } // Trạng thái focus

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        // Hiển thị tiêu đề với dấu sao đỏ (nếu có)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title.replace("*", ""), // Loại bỏ dấu "*" trong tiêu đề
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            if (title.contains("*")) {
                Text(
                    text = "*",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red // Màu đỏ cho dấu sao
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách giữa tiêu đề và TextField

        // TextField với nền thay đổi khi focus
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isFocused) Color(0xFFF5F5F5) else Color.White, // Nền xám khi focus
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)) // Viền xám nhạt
                .height(56.dp) // Chiều cao TextField
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text(text = hint, color = Color.Gray) }, // Placeholder màu xám
                modifier = Modifier
                    .fillMaxSize()
                    .onFocusChanged { isFocused = it.isFocused }, // Cập nhật trạng thái focus
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Xóa màu nền mặc định khi focus
                    unfocusedContainerColor = Color.Transparent, // Xóa màu nền mặc định khi unfocus
                    cursorColor = Color.Black, // Màu con trỏ
                    focusedIndicatorColor = Color.Transparent, // Ẩn viền focus mặc định
                    unfocusedIndicatorColor = Color.Transparent // Ẩn viền unfocus mặc định
                )
            )
        }
    }
}



@Composable
fun AddAssetTopBar(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color(0xFFF5F5F5) // Màu trắng đục
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
                    .border(1.dp, Color.Gray, shape = CircleShape)
                    .background(Color.White, shape = CircleShape)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Tiêu đề và phụ đề
            Column {
                Text(
                    text = "Thêm tài sản",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold // Đậm cho tiêu đề
                )
                Text(
                    text = "Nhà trọ Đức Sơn",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium // Đậm hơn một chút so với mặc định
                )
            }

        }
    }
}

@Composable
fun IconGridSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Chọn icon",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Danh sách icon minh họa
        val icons = listOf(
            Icons.Default.Home, // Icon giả lập
            Icons.Default.Settings,
            Icons.Default.Person,
            Icons.Default.Search,
            Icons.Default.Edit,
            Icons.Default.Info,
            Icons.Default.Add,
            Icons.Default.Close,
            Icons.Default.Check,
            Icons.Default.ArrowBack,
            Icons.Default.ArrowForward,
            Icons.Default.ArrowDropDown
        )

        val selectedIcon = remember { mutableStateOf(-1) } // -1 nghĩa là chưa chọn icon nào

        // Hiển thị các icon với khoảng cách nhỏ hơn
        Column(modifier = Modifier.fillMaxWidth()) {
            icons.chunked(6).forEachIndexed { rowIndex, rowIcons -> // Hiển thị 6 icon trên mỗi hàng
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Khoảng cách giữa các icon
                ) {
                    rowIcons.forEachIndexed { columnIndex, icon ->
                        val index = rowIndex * 6 + columnIndex
                        Box(
                            modifier = Modifier
                                .size(56.dp) // Kích thước mỗi ô icon
                                .background(
                                    color = if (selectedIcon.value == index) Color(0xFFE0E0E0) else Color(0xFFF5F5F5),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (selectedIcon.value == index) Color.Black else Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { selectedIcon.value = index },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = "Icon $index",
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}