package com.imorning.accountbook.ui.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.AccountBookDatabaseDao
import com.imorning.accountbook.entity.BalanceData
import com.imorning.accountbook.entity.ExpenseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class ExpenseViewModel(private val accountBookDatabaseDao: AccountBookDatabaseDao) : ViewModel() {

    fun insert(expenseData: ExpenseData) {
        runBlocking {
            var oldSum: Double? = accountBookDatabaseDao.queryLastBalance()
            if (oldSum == null) {
                oldSum = 0.0
            }
            val balanceData = BalanceData(
                id = 0,
                date = expenseData.date,
                value = expenseData.value,
                type = expenseData.type,
                sum = oldSum - expenseData.value
            )
            accountBookDatabaseDao.insert(expenseData = expenseData)
            accountBookDatabaseDao.insert(balanceData = balanceData)
        }
    }

    fun delete(expenseData: ExpenseData) {
        runBlocking {
            accountBookDatabaseDao.delete(expenseData = expenseData)
        }
    }

    fun queryAll(): Flow<List<ExpenseData>> {
        return accountBookDatabaseDao.queryAllDisburse()
    }

    fun update(expenseData: ExpenseData) {
        runBlocking {
            accountBookDatabaseDao.update(expenseData = expenseData)
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