package dev.tabansi.palm.palmapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.tabansi.palm.palmapp.data.entity.ChatSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatSettingsDao {
    @Query("select * from chat_settings where id = :id")
    fun getChatSettings(id: Int): Flow<ChatSettings>

    @Query("select * from chat_settings")
    fun getAllChatSettings(): Flow<List<ChatSettings>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(chatSettings: ChatSettings)

    @Delete
    suspend fun delete(chatSettings: ChatSettings)

    @Update
    suspend fun update(chatSettings: ChatSettings)
}