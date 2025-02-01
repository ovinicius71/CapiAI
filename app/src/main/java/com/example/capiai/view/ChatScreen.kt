package com.example.capiai.view

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
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.capiai.Api.ChatMessage
import com.example.capiai.Api.ChatViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatViewModel = viewModel()
) {
    val backgroundColor = Color(0xFF0C0D12)
    val secondaryColor = Color(0xFF1A1C23)
    val accentColor = Color(0xFF3369FF)
    val textColor = Color.White

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var inputText by remember { mutableStateOf(TextFieldValue("")) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                viewModel = viewModel,
                backgroundColor = backgroundColor,
                textColor = textColor,
                accentColor = accentColor,
                scope = scope,
                drawerState = drawerState
            )
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
                NewChatButton(
                    viewModel = viewModel,
                    accentColor = accentColor,
                    textColor = textColor
                )
            },
            containerColor = backgroundColor
        ) { innerPadding ->
            MainContent(
                viewModel = viewModel,
                inputText = inputText,
                onInputChange = { inputText = it },
                backgroundColor = backgroundColor,
                secondaryColor = secondaryColor,
                accentColor = accentColor,
                textColor = textColor,
                innerPadding = innerPadding
            )
        }
    }
}

@Composable
private fun DrawerContent(
    viewModel: ChatViewModel,
    backgroundColor: Color,
    textColor: Color,
    accentColor: Color,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
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
                items(viewModel.chatHistory.indices.toList()) { index ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Chat ${index + 1}",
                                color = textColor,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        selected = viewModel.currentChatIndex == index,
                        onClick = {
                            viewModel.currentChatIndex = index
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = accentColor.copy(alpha = 0.2f),
                            unselectedContainerColor = Color.Transparent,
                            selectedTextColor = textColor,
                            unselectedTextColor = textColor
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun NewChatButton(
    viewModel: ChatViewModel,
    accentColor: Color,
    textColor: Color
) {
    FloatingActionButton(
        onClick = {
            viewModel.chatHistory = viewModel.chatHistory + listOf(emptyList())
            viewModel.currentChatIndex = viewModel.chatHistory.lastIndex
        },
        containerColor = accentColor.copy(alpha = 0.2f),
        modifier = Modifier.padding(horizontal = 0.dp, vertical = 100.dp)
    ) {
        Icon(Icons.Default.Add, contentDescription = "New chat", tint = textColor)
    }
}

@Composable
private fun MainContent(
    viewModel: ChatViewModel,
    inputText: TextFieldValue,
    onInputChange: (TextFieldValue) -> Unit,
    backgroundColor: Color,
    secondaryColor: Color,
    accentColor: Color,
    textColor: Color,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        MessagesList(
            messages = viewModel.chatHistory.getOrElse(viewModel.currentChatIndex) { emptyList() },
            accentColor = accentColor,
            secondaryColor = secondaryColor,
            textColor = textColor
        )

        InputRow(
            inputText = inputText,
            onInputChange = onInputChange,
            isLoading = viewModel.isLoading,
            onSend = {
                if (inputText.text.isNotBlank()) {
                    viewModel.sendMessage(inputText.text)
                    onInputChange(TextFieldValue(""))
                }
            },
            secondaryColor = secondaryColor,
            textColor = textColor
        )

        ErrorMessage(viewModel.error, textColor)
    }
}

@Composable
private fun MessagesList(
    messages: List<ChatMessage>,
    accentColor: Color,
    secondaryColor: Color,
    textColor: Color
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(messages) { message ->
            MessageBubble(
                message = message,
                userColor = accentColor.copy(alpha = 0.2f),
                backgroundColor = secondaryColor,
                textColor = textColor
            )
        }
    }
}

@Composable
private fun InputRow(
    inputText: TextFieldValue,
    onInputChange: (TextFieldValue) -> Unit,
    isLoading: Boolean,
    onSend: () -> Unit,
    secondaryColor: Color,
    textColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = inputText,
            onValueChange = onInputChange,
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
            onClick = onSend,
            enabled = !isLoading,
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

@Composable
private fun ErrorMessage(error: String?, textColor: Color) {
    error?.let {
        Text(
            text = it,
            color = Color.Red,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MessageBubble(
    message: ChatMessage,
    userColor: Color,
    backgroundColor: Color,
    textColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (message.isUser) userColor else backgroundColor,
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 2.dp,
            modifier = Modifier
                .padding(4.dp)
                .widthIn(max = 340.dp)
        ) {
            Text(
                text = message.content,
                modifier = Modifier.padding(16.dp),
                color = textColor,
                textAlign = if (message.isUser) TextAlign.End else TextAlign.Start
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen(navController = rememberNavController())
}