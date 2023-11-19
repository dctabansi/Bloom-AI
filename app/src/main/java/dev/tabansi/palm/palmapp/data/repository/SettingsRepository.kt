package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.entity.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun insertSettings(settings: Settings)
    suspend fun updateSettings(settings: Settings)
    fun selectSettingsByChat(chatId: Int): Flow<Settings>
}