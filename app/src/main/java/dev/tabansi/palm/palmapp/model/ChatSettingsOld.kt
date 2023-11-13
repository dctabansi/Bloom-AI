package dev.tabansi.palm.palmapp.model

data class ChatSettingsOld(
    val maxTokens: Int = 4096, // Max output tokens, 1 token = ~4 chars
    val temperature: Float = 0.25f, // degree of randomness
    val topK: Int = 40,
    val topP: Int = 0,
    val candidate: Int = 1
)
