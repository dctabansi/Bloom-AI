package dev.tabansi.palm.palmapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.tabansi.palm.palmapp.data.entity.ChatMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {
    @Query("select * from chat_message where id == :id")
    fun getChatMessage(id: Int): Flow<ChatMessage>

    @Query("select * from chat_message order by timestamp")
    fun getAllChatMessages(): Flow<List<ChatMessage>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(chatMessage: ChatMessage)

    @Delete
    suspend fun delete(chatMessage: ChatMessage)

    @Update
    suspend fun update(chatMessage: ChatMessage)
}