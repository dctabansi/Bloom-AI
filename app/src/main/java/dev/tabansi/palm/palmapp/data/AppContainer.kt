package dev.tabansi.palm.palmapp.data

import android.content.Context
import dev.tabansi.palm.palmapp.data.database.PalmDatabase
import dev.tabansi.palm.palmapp.data.repository.ChatMessageRepository
import dev.tabansi.palm.palmapp.data.repository.ChatRepository
import dev.tabansi.palm.palmapp.data.repository.ChatSettingsRepository
import dev.tabansi.palm.palmapp.data.repository.OfflineChatMessageRepository
import dev.tabansi.palm.palmapp.data.repository.OfflineChatRepository
import dev.tabansi.palm.palmapp.data.repository.OfflineChatSettingsRepository

interface AppContainer {
    val chatRepository: ChatRepository
    val chatMessageRepository: ChatMessageRepository
    val chatSettingsRepository: ChatSettingsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val chatRepository: ChatRepository by lazy {
        OfflineChatRepository(PalmDatabase.getDatabase(context).chatDao())
    }
    override val chatMessageRepository: ChatMessageRepository by lazy {
        OfflineChatMessageRepository(PalmDatabase.getDatabase(context).chatMessageDao())
    }
    override val chatSettingsRepository: ChatSettingsRepository by lazy {
        OfflineChatSettingsRepository(PalmDatabase.getDatabase(context).chatSettingsDao())
    }
}