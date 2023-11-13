package dev.tabansi.palm.palmapp.ui

import androidx.lifecycle.ViewModel
import dev.tabansi.palm.palmapp.data.entity.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel(chatId: Int?) : ViewModel() {
    private var _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState: StateFlow<ChatUiState>
        get() = _chatUiState

    val sampleMessages = listOf<ChatMessage>(

    )
}

data class ChatUiState(
    val title: String = "",
    val titleSet: Boolean = false,
    val messages: List<ChatMessage> = listOf(),
    val inputBlocked: Boolean = false,
    val currentUserInput: String = ""
)