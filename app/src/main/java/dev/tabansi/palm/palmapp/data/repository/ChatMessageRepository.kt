package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.entity.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatMessageRepository {
    fun getChatStream(chatMessageId: Int): Flow<ChatMessage>
    fun getAllChatMessagesStream(): Flow<List<ChatMessage>>
    suspend fun insertChatMessage(chatMessage: ChatMessage)
    suspend fun deleteChatMessage(chatMessage: ChatMessage)
    suspend fun updateChatMessage(chatMessage: ChatMessage)
}