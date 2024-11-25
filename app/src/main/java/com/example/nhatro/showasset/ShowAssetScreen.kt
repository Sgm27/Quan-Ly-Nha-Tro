package com.example.nhatro.showasset

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.nhatro.AddAssetTopBar

@Composable
fun ShowAssetScreen(navController: NavController) {
    // Danh sách tài sản mẫu
    val assetList = listOf(
        Asset(name = "Tủ lạnh", imageUri = null),
        Asset(name = "Máy giặt", imageUri = null),
        Asset(name = "Quạt điện", imageUri = null),
        Asset(name = "Bàn học", imageUri = null)
    )

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

            Text(
                text = "Danh sách tài sản",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            // Hiển thị danh sách tài sản
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(assetList) { asset ->
                    AssetItem(asset = asset)
                }
            }

            // Nút thêm tài sản ở cuối
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Xử lý quay lại */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = "Đóng", color = Color.Black)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /* Xử lý thêm tài sản */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = "+ Thêm tài sản", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun AssetItem(asset: Asset) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
            .clickable { /* Thao tác khi nhấn vào tài sản */ }
            .padding(16.dp)
    ) {
        // Hiển thị hình ảnh tài sản (nếu có)
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.Gray, shape = CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (asset.imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(asset.imageUri),
                    contentDescription = "Hình ảnh tài sản",
                    modifier = Modifier.size(64.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Home, // Icon mặc định nếu không có ảnh
                    contentDescription = "No Image",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Hiển thị thông tin tài sản
        Column {
            Text(
                text = asset.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Nhấn để xem chi tiết",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

data class Asset(
    val name: String,
    val imageUri: Uri? = null // URI của hình ảnh tài sản
)
