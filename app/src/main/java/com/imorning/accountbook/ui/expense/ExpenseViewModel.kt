package com.imorning.accountbook.ui.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.AccountBookDatabaseDao
import com.imorning.accountbook.entity.BalanceRecordEntity
import com.imorning.accountbook.entity.ExpenseRecordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class ExpenseViewModel(private val accountBookDatabaseDao: AccountBookDatabaseDao) : ViewModel() {

    fun insert(expenseRecordEntity: ExpenseRecordEntity) {
        runBlocking {
            var oldSum: Double? = accountBookDatabaseDao.queryLastBalance()
            if (oldSum == null) {
                oldSum = 0.0
            }
            val balanceRecordEntity = BalanceRecordEntity(
                id = 0,
                date = expenseRecordEntity.date,
                value = expenseRecordEntity.value,
                type = expenseRecordEntity.type,
                sum = oldSum - expenseRecordEntity.value
            )
            accountBookDatabaseDao.insert(expenseRecordEntity = expenseRecordEntity)
            accountBookDatabaseDao.insert(balanceRecordEntity = balanceRecordEntity)
        }
    }

    fun delete(expenseRecordEntity: ExpenseRecordEntity) {
        runBlocking {
            accountBookDatabaseDao.delete(expenseRecordEntity = expenseRecordEntity)
        }
    }

    fun queryAll(): Flow<List<ExpenseRecordEntity>> {
        return accountBookDatabaseDao.queryAllDisburse()
    }

    fun update(expenseRecordEntity: ExpenseRecordEntity) {
        runBlocking {
            accountBookDatabaseDao.update(expenseRecordEntity = expenseRecordEntity)
        }
    }

    companion object {
        private const val TAG = "ExpenseViewModel"
    }
}

class ExpenseViewModelFactory(
    private val accountBookDatabaseDao: AccountBookDatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(accountBookDatabaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}