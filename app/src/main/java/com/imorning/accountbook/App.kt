package com.imorning.accountbook

import android.app.Application
import com.imorning.accountbook.database.AccountBookDatabase
import com.imorning.accountbook.ui.home.RecordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database: AccountBookDatabase by lazy {
        AccountBookDatabase.getDatabase(
            this,
            applicationScope
        )
    }
    val repository by lazy { RecordRepository(database.bookDao()) }

    companion object {
        private const val TAG = "App"
    }

}