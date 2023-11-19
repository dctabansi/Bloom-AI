package dev.tabansi.palm.palmapp.data

import android.content.Context
import dev.tabansi.palm.palmapp.data.repository.ChatRepository
import dev.tabansi.palm.palmapp.data.repository.MessageRepository
import dev.tabansi.palm.palmapp.data.repository.OfflineChatRepository
import dev.tabansi.palm.palmapp.data.repository.OfflineMessageRepository
import dev.tabansi.palm.palmapp.data.repository.OfflineSettingsRepository
import dev.tabansi.palm.palmapp.data.repository.SettingsRepository

interface AppContainer {
    val chatRepository: ChatRepository
    val messageRepository: MessageRepository
    val settingsRepository: SettingsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val chatRepository: ChatRepository by lazy {
        OfflineChatRepository(PalmDatabase.getInstance(context).chatDao())
    }
    override val messageRepository: MessageRepository by lazy {
        OfflineMessageRepository(PalmDatabase.getInstance(context).messageDao())
    }
    override val settingsRepository: SettingsRepository by lazy {
        OfflineSettingsRepository(PalmDatabase.getInstance(context).settingsDao())
    }
}