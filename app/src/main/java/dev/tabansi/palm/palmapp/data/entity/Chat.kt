package dev.tabansi.palm.palmapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    @ColumnInfo(name = "title_set")
    val titleSet: Boolean = false,
    @ColumnInfo(name = "last_updated")
    val lastUpdated: Long
) {
    @Ignore
    val message: List<ChatMessage> = listOf()
}
