package dev.tabansi.palm.palmapp.ui

import androidx.lifecycle.ViewModel
import dev.tabansi.palm.palmapp.model.ChatOld

class HomeViewModel : ViewModel() {
}

data class HomeUiState(
    val chatMessages: List<ChatOld>
)