package com.imorning.accountbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.DatabaseDao
import com.imorning.accountbook.entity.IncomeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class DatabaseViewModel(private val databaseDao: DatabaseDao) : ViewModel() {

    fun getAll(): Flow<List<IncomeData>> {
        TODO("get all data")
    }

    fun insert(incomeData: IncomeData) {
        runBlocking {
            databaseDao.insert(incomeData = incomeData)
        }

    }

    fun delete(incomeData: IncomeData) {
        runBlocking {
            databaseDao.delete(incomeData = incomeData)
        }
    }

    fun queryIncome(): Flow<List<IncomeData>> {
        return databaseDao.queryAllIncome()
    }

    fun update(incomeData: IncomeData) {
        runBlocking {
            databaseDao.update(incomeData = incomeData)
        }
    }
}

class DatabaseViewModelFactory(
    private val databaseDao: DatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DatabaseViewModel(databaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}