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
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nhatro.ui.theme.NhaTroTheme
import com.example.nhatro.R

@Composable
fun RegisterAccountScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var registrationMessage by remember { mutableStateOf("") }
    var isCountingDown by remember { mutableStateOf(false) }
    var countdownTime by remember { mutableStateOf(3) }

    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isButtonEnabled = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && isEmailValid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email field
        Column(modifier = Modifier.fillMaxWidth()) {
            TextFieldWithLabel(
                label = "Email",
                placeholder = "Nhập vào email",
                keyboardType = KeyboardType.Email,
                text = email,
                onTextChange = {
                    email = it
                    emailError = if (android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) "" else "Email không hợp lệ"
                },
                labelFontSize = 16f
            )

            // Hiển thị lỗi nếu email không hợp lệ
            if (emailError.isNotEmpty()) {
                Text(
                    text = emailError,
                    fontSize = 12.sp,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        TextFieldWithLabel(
            label = "Mật khẩu",
            placeholder = "Nhập mật khẩu",
            keyboardType = KeyboardType.Password,
            text = password,
            onTextChange = { password = it },
            labelFontSize = 16f,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password field
        TextFieldWithLabel(
            label = "Xác nhận mật khẩu",
            placeholder = "Nhập lại mật khẩu",
            keyboardType = KeyboardType.Password,
            text = confirmPassword,
            onTextChange = { confirmPassword = it },
            labelFontSize = 16f,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        Button(
            onClick = {
                if (password == confirmPassword) {
                    registrationMessage = "Đăng ký tài khoản thành công! Chuyển qua đăng nhập trong $countdownTime giây."
                    isCountingDown = true
                } else {
                    registrationMessage = "Mật khẩu không khớp"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isButtonEnabled) Color(0xFF0B9E43) else Color(0xFFA5D6A7), // Màu đậm hơn khi đủ thông tin
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = "Đăng ký",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display registration message
        if (registrationMessage.isNotEmpty()) {
            Text(
                text = registrationMessage,
                fontSize = 14.sp,
                color = if (registrationMessage.contains("thành công")) Color.Green else Color.Red,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Countdown logic
        if (isCountingDown) {
            LaunchedEffect(Unit) {
                while (countdownTime > 0) {
                    kotlinx.coroutines.delay(1000L) // Đợi 1 giây
                    countdownTime--
                    registrationMessage = "Đăng ký tài khoản thành công! Chuyển qua đăng nhập trong $countdownTime giây."
                }
                navController.navigate("loginScreen") // Chuyển sang màn hình đăng nhập sau khi đếm ngược xong
            }
        }

        // Back to Login Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Quay lại đăng nhập",
                color = Color(0xFF0B9E43), // Màu xanh lá cây
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    navController.navigate("loginScreen") // Chuyển hướng về màn hình đăng nhập
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Footer
        Text(
            text = "© 2024 DS",
            fontSize = 14.sp,
            color = Color(0xFF333333),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_flag),
                contentDescription = "Flag",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Tiếng Việt", fontSize = 14.sp, color = Color(0xFF333333))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Color(0xFF333333)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Version 1.0.0 - 15112024",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}


