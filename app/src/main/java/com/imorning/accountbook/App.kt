package com.imorning.accountbook

import android.app.Application
import com.imorning.accountbook.database.AccountBookDatabase

class App : Application() {
    val database: AccountBookDatabase by lazy { AccountBookDatabase.getDatabase(this) }

    companion object {
        private const val TAG = "App"
    }

}