package com.example.capiai.Api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import retrofit2.Response
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val api = ApiClient.instance

    var chatHistory by mutableStateOf(listOf(listOf<ChatMessage>()))
    var currentChatIndex by mutableStateOf(0)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                error = null

                // Adiciona mensagem do usuário
                addToHistory(ChatMessage(message, true))

                // Chama a API (alteração aqui)
                val response: Response<ChatResponse> = api.getSolution(
                    ChatRequest(messages = listOf(
                        Message("user", "Resolva esta questão passo a passo detalhadamente: $message")
                    ))
                )

                // Verifica se a resposta é bem-sucedida
                if (response.isSuccessful) { // Agora deve reconhecer
                    val aiResponse = response.body()?.choices?.firstOrNull()?.message?.content
                        ?: "Não foi possível obter a resposta"
                    addToHistory(ChatMessage(aiResponse, false))
                } else {
                    error = "Erro na API: ${response.code()}"
                }
            } catch (e: Exception) {
                error = "Erro de conexão: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    private fun addToHistory(message: ChatMessage) {
        chatHistory = chatHistory.toMutableList().apply {
            this[currentChatIndex] = this[currentChatIndex] + message
        }
    }
}

data class ChatMessage(
    val content: String,
    val isUser: Boolean
)