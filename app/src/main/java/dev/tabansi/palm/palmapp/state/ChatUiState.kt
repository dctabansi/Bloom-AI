package dev.tabansi.palm.palmapp.state

import dev.tabansi.palm.palmapp.data.Message

data class ChatUiState(
    val title: String = "",
    val titleSet: Boolean = false,
    val messages: List<Message> = listOf(),
    val inputBlocked: Boolean = false,
    val currentUserInput: String = ""
)
