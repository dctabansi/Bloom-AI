package dev.tabansi.palm.palmapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.tabansi.palm.palmapp.PalmApplication
import dev.tabansi.palm.palmapp.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(palmApplication().container.chatRepository)
        }

        // Initializer for ChatViewModel
//        initializer { chatId: Int ->
//            ChatViewModel(
//                chatId,
//                palmApplication().container.chatRepository,
//                palmApplication().container.messageRepository,
//                this.createSavedStateHandle(),
//            )
//        }
    }
}

fun CreationExtras.palmApplication(): PalmApplication =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PalmApplication