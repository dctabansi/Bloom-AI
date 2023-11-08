package dev.tabansi.palm.palmapp.viewmodels

import androidx.lifecycle.ViewModel
import dev.tabansi.palm.palmapp.state.ChatUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel(){
    private var _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState: StateFlow<ChatUiState>
        get() = _chatUiState.asStateFlow()
}