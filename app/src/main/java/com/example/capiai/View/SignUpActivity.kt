package com.example.capiai.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.navigation.NavController
import com.example.capiai.R

@Composable
fun SignUpScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val accentColor = Color(0xFF3369FF)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0c0d12)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.user_image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(80.dp)
        )

        Text(
            text = "Hello User",
            color = Color.Black
        )

        Text(
            text = "Create your account for better Experience",
            color = Color.White,
            modifier = Modifier.padding(horizontal = 24.dp),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Email",
                    modifier = Modifier.size(24.dp)
                )
            }
            )
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Email",
                    modifier = Modifier.size(24.dp)
                )
            }

        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.message),
                    contentDescription = "Email",
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        OutlinedTextField(
            value = contactNumber,
            onValueChange = { contactNumber = it },
            label = { Text("Contact Number") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.calling),
                    contentDescription = "Email",
                    modifier = Modifier.size(24.dp)
                )
            }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    val icon = if (isPasswordVisible) R.drawable.pass_open else R.drawable.pass_hide
                    Image(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(24.dp))
                }
            }
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = accentColor.copy(alpha = 0.2f)
            ),
            onClick = { navController.navigate("chat") {
                popUpTo("signup") { inclusive = true }
            }
            },

            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(horizontal = 8.dp)
            ) {
            Text(text = "Sign Up", fontSize = 16.sp)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Already have an account?", color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign In",
                color = accentColor.copy(alpha = 0.3f),
                modifier = Modifier.clickable { navController.navigate("login") }
            )
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = NavController(LocalContext.current))
}

