package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.entity.Chat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChatStream(chatId: Int): Flow<Chat?>
    fun getAllChatsStream(): Flow<List<Chat>>
    suspend fun insertChat(chat: Chat)
    suspend fun deleteChat(chat: Chat)
    suspend fun updateChat(chat: Chat)
}