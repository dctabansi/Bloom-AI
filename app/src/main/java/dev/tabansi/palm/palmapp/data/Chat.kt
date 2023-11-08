package dev.tabansi.palm.palmapp.data

data class Chat(
    val id: Int = chatIdGenerator,
    var title: String = "",
    val titleSet: Boolean = false,
    val messages: MutableList<Message> = mutableListOf(),
    val chatSettings: ChatSettings = AppData.Settings.defaultChatSettings
) {
    companion object {
        var chatIdGenerator = 0
            get() = field++
    }

    private var messageIdGenerator = 0
        get() = field++

    fun addMessage(text: String) {
        messages.add(
            Message(
                id = messageIdGenerator,
                text = text
            )
        )
    }

    fun getMessageList(): List<Message> = messages
}
