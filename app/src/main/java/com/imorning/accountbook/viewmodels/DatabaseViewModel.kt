package com.imorning.accountbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.AccountBookDatabaseDao
import com.imorning.accountbook.entity.ExpenseData
import com.imorning.accountbook.entity.IncomeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class DatabaseViewModel(private val accountBookDatabaseDao: AccountBookDatabaseDao) : ViewModel() {

    fun getAll(): Flow<List<IncomeData>> {
        TODO("get all data")
    }

    fun insert(incomeData: IncomeData) {
        runBlocking {
            accountBookDatabaseDao.insert(incomeData = incomeData)
        }
    }

    fun insert(expenseData: ExpenseData) {
        runBlocking {
            accountBookDatabaseDao.insert(expenseData = expenseData)
        }
    }

    fun delete(incomeData: IncomeData) {
        runBlocking {
            accountBookDatabaseDao.delete(incomeData = incomeData)
        }
    }

    fun queryIncome(): Flow<List<IncomeData>> {
        return accountBookDatabaseDao.queryAllIncome()
    }

    fun update(incomeData: IncomeData) {
        runBlocking {
            accountBookDatabaseDao.update(incomeData = incomeData)
        }
    }
}

class DatabaseViewModelFactory(
    private val accountBookDatabaseDao: AccountBookDatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DatabaseViewModel(accountBookDatabaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}