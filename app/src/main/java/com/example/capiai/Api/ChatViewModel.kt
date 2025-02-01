package com.example.capiai.Api

import android.util.Log
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

    suspend fun validateApiKey(): Boolean {
        return try {
            val testRequest = ChatRequest(messages = listOf(Message("user", "Teste")))
            val response = api.getSolution(testRequest)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    // ChatViewModel.kt
    fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                error = null

                addToHistory(ChatMessage(message, true))

                // Chama a API
                val response = api.getSolution(
                    ChatRequest(
                        messages = listOf(Message("user", "Resolva esta questão passo a passo: $message"))
                    )
                )

                // Verifica a resposta
                if (response.isSuccessful) {
                    val aiResponse = response.body()?.choices?.firstOrNull()?.message?.content
                        ?: "Resposta inválida"
                    addToHistory(ChatMessage(aiResponse, false))
                } else {
                    error = "Erro: ${response.code()} - ${response.errorBody()?.string()}"
                    Log.e("API", "Erro: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                error = "Erro de rede: ${e.localizedMessage}"
                Log.e("API", "Exceção: ${e.stackTraceToString()}")
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

