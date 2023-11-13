package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.dao.ChatMessageDao
import dev.tabansi.palm.palmapp.data.entity.ChatMessage
import kotlinx.coroutines.flow.Flow

class OfflineChatMessageRepository(
    private val chatMessageDao: ChatMessageDao
) : ChatMessageRepository {
    override fun getChatStream(chatMessageId: Int): Flow<ChatMessage> =
        chatMessageDao.getChatMessage(chatMessageId)

    override fun getAllChatMessagesStream(): Flow<List<ChatMessage>> =
        chatMessageDao.getAllChatMessages()

    override suspend fun insertChatMessage(chatMessage: ChatMessage) =
        chatMessageDao.insert(chatMessage)

    override suspend fun deleteChatMessage(chatMessage: ChatMessage) =
        chatMessageDao.delete(chatMessage)

    override suspend fun updateChatMessage(chatMessage: ChatMessage) =
        chatMessageDao.update(chatMessage)
}