package dev.tabansi.palm.palmapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.tabansi.palm.palmapp.data.entity.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("select * from chat where id = :id")
    fun getChat(id: Int): Flow<Chat>

    @Query("select * from chat order by last_updated desc")
    fun getAllChats(): Flow<List<Chat>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(chat: Chat)

    @Delete
    suspend fun delete(chat: Chat)

    @Update
    suspend fun update(chat: Chat)}