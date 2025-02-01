package com.example.capiai.view

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

@Composable
fun SplashScreen(navController: NavController, onSplashFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0c0d12)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_capiai),
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
        )
    }
}