package com.imorning.accountbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imorning.accountbook.dao.DatabaseDao
import com.imorning.accountbook.entity.BalanceData
import com.imorning.accountbook.entity.DisburseData
import com.imorning.accountbook.entity.IncomeData
import com.imorning.accountbook.utils.Const
import com.imorning.accountbook.utils.Converters

@Database(
    entities = [IncomeData::class, DisburseData::class, BalanceData::class],
    version = 1
)
@TypeConverters(Converters::class)

abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): DatabaseDao

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