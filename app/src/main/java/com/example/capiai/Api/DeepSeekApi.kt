package com.example.capiai.Api

import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepSeekApi {
    @POST("chat/completions")
    suspend fun getSolution(
        @Body request: ChatRequest
    ): retrofit2.Response<ChatResponse> // Usando retrofit2.Response
}

data class ChatRequest(
    val model: String = "deepseek/deepseek-r1",
    val messages: List<Message>,
    val temperature: Double = 0.7
)

data class Message(
    val role: String,
    val content: String
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)

object ApiClient {
    private const val BASE_URL = "https://openrouter.ai/api/v1/"
    private const val API_KEY = "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"

    val instance: DeepSeekApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer $API_KEY")
                            .addHeader("Content-Type", "application/json")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeepSeekApi::class.java)
    }
}