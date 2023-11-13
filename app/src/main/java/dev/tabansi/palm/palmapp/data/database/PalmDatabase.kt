package dev.tabansi.palm.palmapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.tabansi.palm.palmapp.data.dao.ChatDao
import dev.tabansi.palm.palmapp.data.dao.ChatMessageDao
import dev.tabansi.palm.palmapp.data.dao.ChatSettingsDao
import dev.tabansi.palm.palmapp.data.entity.Chat
import dev.tabansi.palm.palmapp.data.entity.ChatMessage
import dev.tabansi.palm.palmapp.data.entity.ChatSettings

@Database(
    entities = [Chat::class,ChatMessage::class, ChatSettings::class],
    version = 1,
    exportSchema = false
)
abstract class PalmDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
    abstract fun chatMessageDao(): ChatMessageDao
    abstract fun chatSettingsDao(): ChatSettingsDao

    companion object {
        @Volatile
        private var Instance: PalmDatabase? = null
        fun getDatabase(context: Context): PalmDatabase =
            Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PalmDatabase::class.java, "palm_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
    }
}