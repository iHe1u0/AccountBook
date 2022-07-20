package com.imorning.accountbook.ui.income

import androidx.lifecycle.ViewModel
import com.imorning.accountbook.dao.DatabaseDao
import com.imorning.accountbook.entity.IncomeData
import kotlinx.coroutines.flow.Flow

class IncomeViewModel(private val databaseDao: DatabaseDao) : ViewModel() {
    fun queryAll(): Flow<List<IncomeData>> = databaseDao.queryAllIncome()
}