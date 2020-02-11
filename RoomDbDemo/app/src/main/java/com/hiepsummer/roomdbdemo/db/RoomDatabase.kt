package com.hiepsummer.roomdbdemo.db

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hiepsummer.roomdbdemo.WordDao
import com.hiepsummer.roomdbdemo.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    private class WordRoomDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCAE?.let { database ->
                scope.launch {
                    var wordDao = database.wordDao()

                    //delete oldData
                    wordDao.deleteAll()

                    //add newData
                    var word = Word("Helllo")
                    wordDao.insert(word)
                    word = Word("World")
                    wordDao.insert(word)
                    word = Word("Test")
                    wordDao.insert(word)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCAE: WordRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
            return INSTANCAE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).addCallback(
                    WordRoomDatabaseCallback(
                        scope
                    )
                ).build()
                INSTANCAE = instance
                instance
            }
        }
    }
}