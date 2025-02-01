package com.example.capiai.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController) {
    val backgroundColor = Color(0xFF0C0D12)
    val secondaryColor = Color(0xFF1A1C23)
    val accentColor = Color(0xFF3369FF)
    val textColor = Color.White

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var currentChatIndex by remember { mutableStateOf(0) }
    var chatHistory by remember { mutableStateOf(listOf(listOf("Welcome to your new chat!"))) }
    var inputText by remember { mutableStateOf(TextFieldValue("")) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .width(240.dp)
                    .fillMaxHeight()
                    .background(backgroundColor)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Chat History",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp),
                        color = textColor
                    )

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(chatHistory.indices.toList()) { index ->
                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        "Chat ${index + 1}",
                                        color = textColor,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                selected = currentChatIndex == index,
                                onClick = {
                                    currentChatIndex = index
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                                    .fillMaxWidth(),
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = accentColor.copy(alpha = 0.2f),
                                    unselectedContainerColor = secondaryColor,
                                    selectedTextColor = textColor,
                                    unselectedTextColor = textColor
                                )
                            )
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Chat Assistant", color = textColor) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Open menu",
                                tint = textColor
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = backgroundColor
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        chatHistory = chatHistory + listOf(listOf("New chat started"))
                        currentChatIndex = chatHistory.lastIndex
                    },
                    containerColor = accentColor.copy(alpha = 0.2f),

                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 100.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "New chat", tint = textColor)
                }
            },
            containerColor = backgroundColor
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(backgroundColor)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    items(chatHistory[currentChatIndex]) { message ->
                        MessageBubble(
                            message = message,
                            userColor = accentColor.copy(alpha = 0.2f),
                            backgroundColor = secondaryColor
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        modifier = Modifier.weight(1f),
                        shape = CircleShape,
                        placeholder = {
                            Text(
                                "Digite sua mensagem...",
                                color = textColor.copy(alpha = 0.7f)
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = secondaryColor,
                            unfocusedContainerColor = secondaryColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = textColor,
                            unfocusedTextColor = textColor
                        )
                    )
                    IconButton(
                        onClick = {
                            if (inputText.text.isNotBlank()) {
                                chatHistory = chatHistory.toMutableList().apply {
                                    this[currentChatIndex] = this[currentChatIndex] + "You: ${inputText.text}"
                                }
                                inputText = TextFieldValue("")
                            }
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = "Enviar mensagem",
                            tint = textColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MessageBubble(message: String, userColor: Color, backgroundColor: Color) {
    val isUserMessage = message.startsWith("You:")
    val textColor = Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isUserMessage) userColor else backgroundColor,
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 2.dp,
            modifier = Modifier
                .padding(4.dp)
                .widthIn(max = 340.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(16.dp),
                color = textColor,
                textAlign = if (isUserMessage) TextAlign.End else TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(navController = rememberNavController())
}