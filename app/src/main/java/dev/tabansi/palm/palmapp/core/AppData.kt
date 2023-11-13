package dev.tabansi.palm.palmapp.core

import dev.tabansi.palm.palmapp.model.ChatOld
import dev.tabansi.palm.palmapp.model.ChatSettingsOld

object AppData {
    object Settings {
        var systemDefaultTheme: Boolean = true
        var darkTheme: Boolean? = null
        var defaultChatSettings: ChatSettingsOld = ChatSettingsOld()
        private val originalSettings = defaultChatSettings.copy()
    }

    val chatMessages = mutableListOf<ChatOld>()


}