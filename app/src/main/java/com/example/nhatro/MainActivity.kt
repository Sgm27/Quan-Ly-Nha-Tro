package com.example.nhatro
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nhatro.ui.theme.NhaTroTheme
import com.example.nhatro.R
import com.example.nhatro.moreinfo.MoreInformationScreen
import com.example.nhatro.showasset.ShowAssetScreen
import com.example.nhatro.contract.CreateContractScreen
import com.example.nhatro.makeinvoice.MakeInvoiceScreen
import com.example.nhatro.userinfo.UserInformationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NhaTroTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController() // Khởi tạo NavController

    NavHost(navController = navController, startDestination = "makeInvoice") {
        composable("loginScreen") { LoginScreen(navController) }
        composable("registerAccount") { RegisterAccountScreen(navController) }
        composable("rentalHouse") { RentalHouseScreen(navController) }
        composable("addressLocation") { AddressLocationScreen(navController) }
        composable("homeScreen") { HomeScreen(navController) }
        composable("addAsset") { AddAssetScreen(navController) }
        composable("addContract") { AddContractScreen(navController) }
        composable("showAsset") { ShowAssetScreen(navController) }
        composable("moreInfo") { MoreInformationScreen(navController) }
        composable("userInfo") { UserInformationScreen(navController) }
        composable("contractInfo") { CreateContractScreen(navController, "123") }
        composable("makeInvoice") { MakeInvoiceScreen(navController, "123") }
    }
}

@Composable
fun RentalHouseScreen(navController: NavController) {
    var roomCount by remember { mutableStateOf("") }
    var hostelName by remember { mutableStateOf("") }
    var isAutomaticRoomCreation by remember { mutableStateOf(true) }
    var floorCount by remember { mutableStateOf("") }

    Column(
    modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {
        // Header
        Text(
            text = "Thêm mới nhà cho thuê",
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

        )

        Spacer(modifier = Modifier.height(16.dp))

        // Thông tin nhà cho thuê
        SectionHeader(
            title = "Thông tin nhà cho thuê",
            description = "Thông tin cơ bản tên, loại hình..."
        )

        // Loại hình cho thuê
        LabelWithAsterisk(label = "Loại hình cho thuê")
        OutlinedTextField(
            value = "Nhà trọ",
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { /* Xử lý khi cần */ }) {
                    Icon(Icons.Default.Clear, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tên nhà trọ
        LabelWithAsterisk(label = "Tên nhà trọ")
        OutlinedTextField(
            value = hostelName,
            onValueChange = { hostelName = it },
            placeholder = { Text("Ví dụ: Đức Sơn") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.small)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Nhà trọ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            },
            singleLine = true,
            maxLines = 1
        )


        Spacer(modifier = Modifier.height(8.dp))

        // Thiết lập số tầng
        LabelWithAsterisk(label = "Thiết lập số tầng (Gồm tầng trệt)")
        OutlinedTextField(
            value = floorCount,
            onValueChange = { floorCount = it },
            placeholder = { Text("Ví dụ: 3") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                if (floorCount.isNotEmpty()) {
                    IconButton(onClick = { floorCount = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Xóa nội dung")
                    }
                }
            }
        )


        Spacer(modifier = Modifier.height(8.dp))

        // Tự động tạo phòng
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, shape = MaterialTheme.shapes.small) // Thêm viền mỏng
                .padding(8.dp) // Khoảng cách bên trong viền
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Tự động tạo phòng?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Text(
                    text = "Tạo phòng tự động theo tổng số phòng bạn nhập bên dưới sẽ giúp việc tạo phòng nhanh hơn.",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Switch(
                checked = isAutomaticRoomCreation,
                onCheckedChange = { isAutomaticRoomCreation = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF4CAF50), // Màu xanh lá cây
                    checkedTrackColor = Color(0xFFB2DFDB),
                    uncheckedThumbColor = Color.LightGray,
                    uncheckedTrackColor = Color(0xFFD6D6D6)
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }


        Spacer(modifier = Modifier.height(8.dp))

        // Số phòng

        LabelWithAsterisk(label = "Số phòng")

        // Trường nhập liệu cho số phòng
        OutlinedTextField(
            value = roomCount,
            onValueChange = { roomCount = it },
            placeholder = { Text("Ví dụ: 5") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.small)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "phòng",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            },
            singleLine = true,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Địa chỉ & vị trí
        SectionHeader(
            title = "Địa chỉ & vị trí",
            description = "Địa chỉ giúp khách tìm đến chính xác để xem nhà..."
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Thêm địa chỉ & vị trí trên bản đồ
        OutlinedButton(
            onClick = { /* Xử lý khi người dùng nhấn nút */
                navController.navigate("addressLocation")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pluscircle_icon), // Sử dụng ảnh từ drawable
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "Thêm địa chỉ & vị trí trên bản đồ",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium, // Đặt font chữ đậm vừa
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút Đóng và Tiếp theo
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Khoảng cách giữa hai nút
        ) {
            // Nút "Đóng" với màu xám đậm hơn và không có border
            Button(
                onClick = { /* Xử lý khi nhấn nút Đóng */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE0E0E0), // Màu nền xám hơi đậm
                    contentColor = Color.Black // Màu chữ đen
                ),
                shape = RoundedCornerShape(4.dp) // Góc hơi tròn nhẹ
            ) {
                Text(
                    text = "Đóng",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Nút "Tiếp theo"
            Button(
                onClick = { /* Xử lý khi nhấn nút Tiếp theo */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50), // Màu nền xanh lá cây
                    contentColor = Color.White // Màu chữ trắng
                ),
                shape = RoundedCornerShape(4.dp) // Góc hơi tròn nhẹ
            ) {
                Text(
                    text = "Tiếp theo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }



    }
}

@Composable
fun SectionHeader(title: String, description: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Icon Box for #
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF4CAF50), shape = MaterialTheme.shapes.small),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "#",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Title and description
        Column {
            Text(
                text = title,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun LabelWithAsterisk(label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = "*",
            color = Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}



