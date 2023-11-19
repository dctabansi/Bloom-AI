package dev.tabansi.palm.palmapp.data.repository

import dev.tabansi.palm.palmapp.data.dao.SettingsDao
import dev.tabansi.palm.palmapp.data.entity.Settings
import kotlinx.coroutines.flow.Flow

class OfflineSettingsRepository(private val settingsDao: SettingsDao) : SettingsRepository {
    override suspend fun insertSettings(settings: Settings) = settingsDao.insert(settings)
    override suspend fun updateSettings(settings: Settings) = settingsDao.update(settings)
    override fun selectSettingsByChat(chatId: Int): Flow<Settings> = settingsDao.select(chatId)
}