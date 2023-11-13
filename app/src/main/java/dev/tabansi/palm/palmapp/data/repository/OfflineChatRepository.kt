package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.dao.ChatDao
import dev.tabansi.palm.palmapp.data.entity.Chat
import kotlinx.coroutines.flow.Flow

class OfflineChatRepository(
    private val chatDao: ChatDao
) : ChatRepository {
    override fun getChatStream(chatId: Int): Flow<Chat?> =
        chatDao.getChat(chatId)

    override fun getAllChatsStream(): Flow<List<Chat>> =
        chatDao.getAllChats()

    override suspend fun insertChat(chat: Chat) =
        chatDao.insert(chat)

    override suspend fun deleteChat(chat: Chat) =
        chatDao.delete(chat)

    override suspend fun updateChat(chat: Chat) =
        chatDao.update(chat)
}