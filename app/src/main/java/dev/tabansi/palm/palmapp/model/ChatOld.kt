package dev.tabansi.palm.palmapp.model

import dev.tabansi.palm.palmapp.core.AppData

data class ChatOld(
    val id: Int = 0,
    var title: String = "",
    var titleSet: Boolean = false,
    val messages: MutableList<MessageOld> = mutableListOf(),
    val chatSettings: ChatSettingsOld = AppData.Settings.defaultChatSettings.copy()
) {
    companion object {
        var chatIdGenerator = 0
            get() = field++
    }

    private var messageIdGenerator = 0
        get() = field++

    fun addMessage(text: String) {
        messages.add(
            MessageOld(
                id = messageIdGenerator,
                user = if (id % 2 == 0) "user" else "palm",
                text = text
            )
        )
    }

    fun getMessageList(): List<MessageOld> = messages
}
