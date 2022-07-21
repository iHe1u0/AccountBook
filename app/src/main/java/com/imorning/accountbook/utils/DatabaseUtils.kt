package com.imorning.accountbook.utils

import android.content.Context
import androidx.room.Room
import com.imorning.accountbook.database.AccountBookDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

object DatabaseUtils {

    fun changePassword(context: Context, oldPassword: String, newPassword: String) {
        val passphrase = SQLiteDatabase.getBytes(oldPassword.toCharArray())
        val factory = SupportFactory(passphrase)

        val database = Room.databaseBuilder(
            context,
            AccountBookDatabase::class.java,
            Const.DATABASE_NAME
        )
            .openHelperFactory(factory)
            .build()
        database.query("PRAGMA rekey = '$newPassword';", emptyArray())
    }

}