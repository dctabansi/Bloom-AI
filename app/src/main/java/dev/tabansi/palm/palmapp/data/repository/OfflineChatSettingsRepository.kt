package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.dao.ChatSettingsDao
import dev.tabansi.palm.palmapp.data.entity.ChatSettings
import kotlinx.coroutines.flow.Flow

class OfflineChatSettingsRepository(
    private val chatSettingsDao: ChatSettingsDao
) : ChatSettingsRepository {
    override fun getChatSettingsStream(chatSettingsId: Int): Flow<ChatSettings> =
        chatSettingsDao.getChatSettings(chatSettingsId)

    override fun getAllChatSettingsStream(): Flow<List<ChatSettings>> =
        chatSettingsDao.getAllChatSettings()

    override suspend fun insertChatSettings(chatSettings: ChatSettings) =
        chatSettingsDao.insert(chatSettings)

    override suspend fun deleteChatSettings(chatSettings: ChatSettings) =
        chatSettingsDao.delete(chatSettings)

    override suspend fun updateChatSettings(chatSettings: ChatSettings) =
        chatSettingsDao.update(chatSettings)
}