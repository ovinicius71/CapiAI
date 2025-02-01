package com.example.capiai.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.NavigationBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capiai.R
import androidx.compose.material3.NavigationBarItem

@Composable
fun MainScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Wallet", "Chats", "Profile")

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(id = getIconForItem(item)),
                                contentDescription = item,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                0 -> HomeScreen()
                1 -> WalletScreen()
                2 -> NotifyScreen()
                3 -> ProfileScreen()
            }
        }
    }
}

// Exemplo com ícones personalizados (arquivos PNG na pasta res/drawable)
fun getIconForItem(item: String): Int {
    return when (item) {
        "Home" -> R.drawable.ic_home
        "Wallet" -> R.drawable.ic_wallet
        "Chats" -> R.drawable.ic_notify
        "Profile" -> R.drawable.ic_profile
        else -> R.drawable.calling
    }
}