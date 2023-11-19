package dev.tabansi.palm.palmapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.tabansi.palm.palmapp.data.dao.ChatDao
import dev.tabansi.palm.palmapp.data.dao.SettingsDao
import dev.tabansi.palm.palmapp.data.dao.MessageDao
import dev.tabansi.palm.palmapp.data.entity.Chat
import dev.tabansi.palm.palmapp.data.entity.Settings
import dev.tabansi.palm.palmapp.data.entity.Message

@Database(
    entities = [Chat::class, Message::class, Settings::class],
    version = 1,
    exportSchema = false
)
abstract class PalmDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var Instance: PalmDatabase? = null
        fun getInstance(context: Context): PalmDatabase =
            Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PalmDatabase::class.java, "palm_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
    }
}