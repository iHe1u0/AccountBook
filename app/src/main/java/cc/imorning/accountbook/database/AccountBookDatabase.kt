package cc.imorning.accountbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cc.imorning.accountbook.dao.AccountBookDatabaseDao
import cc.imorning.accountbook.entity.BalanceRecordEntity
import cc.imorning.accountbook.entity.ExpenseRecordEntity
import cc.imorning.accountbook.entity.IncomeRecordEntity
import cc.imorning.accountbook.utils.Config
import cc.imorning.accountbook.utils.Converters
import kotlinx.coroutines.CoroutineScope
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


@Database(
    entities = [IncomeRecordEntity::class, ExpenseRecordEntity::class, BalanceRecordEntity::class],
    version = 3
)
@TypeConverters(Converters::class)

abstract class AccountBookDatabase : RoomDatabase() {

    abstract fun bookDao(): AccountBookDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AccountBookDatabase? = null

        fun getDatabase(context: Context): AccountBookDatabase {
            return INSTANCE ?: synchronized(this) {
                val passphrase: ByteArray =
                    SQLiteDatabase.getBytes("pwd".toCharArray())
                val factory = SupportFactory(passphrase)
                val instance = Room.databaseBuilder(
                    context,
                    AccountBookDatabase::class.java,
                    Config.DATABASE_NAME
                )
                    .openHelperFactory(factory)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}