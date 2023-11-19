package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.dao.MessageDao
import dev.tabansi.palm.palmapp.data.entity.Message
import kotlinx.coroutines.flow.Flow

class OfflineMessageRepository(private val messageDao: MessageDao) : MessageRepository {
    override suspend fun insertMessage(message: Message) = messageDao.insert(message)
    override fun selectMessage(id: Int): Flow<Message> = messageDao.select(id)
    override fun selectMessagesByChat(chatId: Int): Flow<List<Message>> =
        messageDao.selectAllByChat(chatId)
}