package com.imorning.accountbook.ui.home

import com.imorning.accountbook.dao.AccountBookDatabaseDao
import kotlinx.coroutines.flow.Flow

class RecordRepository(private val databaseDao: AccountBookDatabaseDao) {

    val incomeType: Flow<List<String>> = databaseDao.queryIncomeType()

    val incomeValue: Flow<List<Double>> = databaseDao.queryIncomeValue()

    val expenseType: Flow<List<String>> = databaseDao.queryExpenseType()

    val expenseValue: Flow<List<Double>> = databaseDao.queryExpenseValue()

}