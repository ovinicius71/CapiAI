package com.example.capiai.Api

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString

@Composable
fun FormattedText(text: String) {
    val annotatedString = buildAnnotatedString {
        // Implemente sua lógica de parsing de Markdown aqui
        append(text)
    }

    Text(text = annotatedString)
}