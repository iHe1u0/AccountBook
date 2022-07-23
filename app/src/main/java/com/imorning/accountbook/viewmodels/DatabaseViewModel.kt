package com.imorning.accountbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.AccountBookDatabaseDao
import com.imorning.accountbook.entity.ExpenseRecordEntity
import com.imorning.accountbook.entity.IncomeRecordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class DatabaseViewModel(private val accountBookDatabaseDao: AccountBookDatabaseDao) : ViewModel() {

    fun getAll(): Flow<List<IncomeRecordEntity>> {
        TODO("get all data")
    }

    fun insert(incomeRecordEntity: IncomeRecordEntity) {
        runBlocking {
            accountBookDatabaseDao.insert(incomeRecordEntity = incomeRecordEntity)
        }
    }

    fun insert(expenseRecordEntity: ExpenseRecordEntity) {
        runBlocking {
            accountBookDatabaseDao.insert(expenseRecordEntity = expenseRecordEntity)
        }
    }

    fun delete(incomeRecordEntity: IncomeRecordEntity) {
        runBlocking {
            accountBookDatabaseDao.delete(incomeRecordEntity = incomeRecordEntity)
        }
    }

    fun queryIncome(): Flow<List<IncomeRecordEntity>> {
        return accountBookDatabaseDao.queryAllIncome()
    }

    fun update(incomeRecordEntity: IncomeRecordEntity) {
        runBlocking {
            accountBookDatabaseDao.update(incomeRecordEntity = incomeRecordEntity)
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