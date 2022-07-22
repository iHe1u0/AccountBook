package com.imorning.accountbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imorning.accountbook.dao.AccountBookDatabaseDao
import com.imorning.accountbook.entity.BalanceData
import com.imorning.accountbook.entity.ExpenseData
import com.imorning.accountbook.entity.IncomeData
import com.imorning.accountbook.utils.Const
import com.imorning.accountbook.utils.Converters
import kotlinx.coroutines.CoroutineScope
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


@Database(
    entities = [IncomeData::class, ExpenseData::class, BalanceData::class],
    version = 3
)
@TypeConverters(Converters::class)

abstract class AccountBookDatabase : RoomDatabase() {

    abstract fun bookDao(): AccountBookDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AccountBookDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AccountBookDatabase {
            return INSTANCE ?: synchronized(this) {
                val passphrase: ByteArray =
                    SQLiteDatabase.getBytes("pwd".toCharArray())
                val factory = SupportFactory(passphrase)
                val instance = Room.databaseBuilder(
                    context,
                    AccountBookDatabase::class.java,
                    Const.DATABASE_NAME
                )
                    .openHelperFactory(factory)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}