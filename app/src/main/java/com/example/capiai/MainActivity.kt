package com.example.capiai


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capiai.screens.ChatScreen
import com.example.capiai.screens.FrameScreen
import com.example.capiai.ui.theme.CapiAITheme
import com.example.capiai.screens.LoginScreen
import com.example.capiai.screens.SignUpScreen
import com.example.capiai.screens.SplashScreen
import com.example.capiai.screens.MainScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapiAITheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash") {
                            SplashScreen(
                                navController = navController,
                                onSplashFinished = {
                                    navController.navigate("frame") {
                                        popUpTo("splash") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("frame") { FrameScreen(navController = navController) }
                        composable("login") { LoginScreen(navController = navController) }
                        composable("signup") { SignUpScreen(navController = navController) }
                        composable("main") { MainScreen(navController) }
                        composable("chat") { ChatScreen(navController = navController) }
                    }
                }
            }
        }
    }
}