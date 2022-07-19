package com.imorning.accountbook

import android.app.Application
import com.imorning.accountbook.database.BookDatabase

class App : Application() {
    val database: BookDatabase by lazy { BookDatabase.getDatabase(this) }

    companion object {
        private const val TAG = "App"
    }
}