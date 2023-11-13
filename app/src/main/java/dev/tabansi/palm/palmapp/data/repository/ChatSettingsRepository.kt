package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.entity.ChatSettings
import kotlinx.coroutines.flow.Flow

interface ChatSettingsRepository {
    fun getChatSettingsStream(chatSettingsId: Int): Flow<ChatSettings>
    fun getAllChatSettingsStream(): Flow<List<ChatSettings>>
    suspend fun insertChatSettings(chatSettings: ChatSettings)
    suspend fun deleteChatSettings(chatSettings: ChatSettings)
    suspend fun updateChatSettings(chatSettings: ChatSettings)
}