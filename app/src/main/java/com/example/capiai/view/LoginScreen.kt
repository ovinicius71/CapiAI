package com.example.capiai.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.capiai.R

@Composable
fun LoginScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val accentColor = Color(0xFF3369FF)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0c0d12))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Welcome",
            fontSize = 22.sp,
            color = Color.White // Corrigido para visibilidade no fundo escuro
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Please enter your email and password",
            fontSize = 13.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your email", color = Color.White) },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.message),
                    contentDescription = "Email",
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A1C23),
                unfocusedContainerColor = Color(0xFF1A1C23),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it }, // Corrigida a formatação
            label = { Text("Enter your Password", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible },
                    modifier = Modifier.size(30.dp) // Modifier aplicado corretamente
                ) {
                    val icon = if (isPasswordVisible) R.drawable.pass_open else R.drawable.pass_hide
                    Image(
                        painter = painterResource(id = icon),
                        modifier = Modifier.size(24.dp),
                        contentDescription = "Toggle Password Visibility" // Descrição acessível
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A1C23),
                unfocusedContainerColor = Color(0xFF1A1C23),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(400.dp))

        Button(
            onClick = {
                navController.navigate("chat") {
                    popUpTo("login") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = accentColor.copy(alpha = 0.2f)
            )
        ) {
            Text(text = "Login", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(21.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don’t have an account? ", color = Color.Gray)
            Text(
                "Sign Up",
                color = accentColor.copy(alpha = 0.3f),
                modifier = Modifier.clickable { navController.navigate("signup") }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Or Continue With", color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(80.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Login with Google",
                modifier = Modifier.size(46.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "Login with Phone",
                modifier = Modifier.size(46.dp)
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}