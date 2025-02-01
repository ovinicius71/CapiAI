package com.example.capiai.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.capiai.R
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.Style

@Composable
fun FrameScreen(navController: NavController) {
    val accentColor = Color(0xFF3369FF)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0c0d12))
    ) {
        Text(
            text = "You AI Assistant",
            color = accentColor.copy(alpha = 0.3f),
            fontSize = 23.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp)
        )
        Text(
            text = "Using this software,you can ask you\n" +
                    "questions and receive articles using\n" +
                    "artificial intelligence assistant",
            color = Color.White,
            fontSize = 13.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )
        // Imagem centralizada
        Image(
            painter = painterResource(id = R.drawable.in_frame),
            contentDescription = "Explication App",
            modifier = Modifier
                .size(400.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.Center) // Centraliza a imagem
        )

        // Botão na parte inferior
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = accentColor.copy(alpha = 0.2f)
            ),
            onClick = {
                navController.navigate("login") {
                    popUpTo("Frame") { inclusive = true }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter) // Posiciona na parte inferior
                .padding(bottom = 40.dp) // Espaço da borda inferior
                .fillMaxWidth()
                .height(45.dp)
                .padding(horizontal = 16.dp), // Espaço nas laterais
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Login", fontSize = 16.sp, color = Color.White)
        }
    }
}
@Preview
@Composable
fun FrameScreenPreview() {
    FrameScreen(navController = NavController(LocalContext.current))
}