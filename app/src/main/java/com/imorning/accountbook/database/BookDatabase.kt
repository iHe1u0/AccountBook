package com.imorning.accountbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imorning.accountbook.dao.BookDao
import com.imorning.accountbook.entity.Book
import com.imorning.accountbook.utils.Const

@Database(entities = arrayOf(Book::class), version = 220719)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    BookDatabase::class.java,
                    Const.DATABASE_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}