package com.imorning.accountbook.ui.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.AccountBookDatabaseDao
import com.imorning.accountbook.entity.BalanceData
import com.imorning.accountbook.entity.IncomeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class IncomeViewModel(private val accountBookDatabaseDao: AccountBookDatabaseDao) : ViewModel() {

    fun insert(incomeData: IncomeData) {
        runBlocking {
            var oldSum: Double? = accountBookDatabaseDao.queryLastBalance()
            if (oldSum == null) {
                oldSum = 0.0
            }
            val balanceData = BalanceData(
                id = 0,
                date = incomeData.date,
                value = incomeData.value,
                type = incomeData.type,
                sum = oldSum + incomeData.value
            )
            accountBookDatabaseDao.insert(incomeData = incomeData)
            accountBookDatabaseDao.insert(balanceData = balanceData)
        }
    }

    fun delete(incomeData: IncomeData) {
        runBlocking {
            accountBookDatabaseDao.delete(incomeData = incomeData)
        }
    }

    fun queryAll(): Flow<List<IncomeData>> {
        return accountBookDatabaseDao.queryAllIncome()
    }

    fun queryById(id: Int): Flow<IncomeData> {
        return accountBookDatabaseDao.queryIncome(id)
    }

    fun queryByType(type: String): Flow<List<IncomeData>> {
        return accountBookDatabaseDao.queryIncomeByType(type)
    }

    fun update(incomeData: IncomeData) {
        runBlocking {
            accountBookDatabaseDao.update(incomeData = incomeData)
        }
    }
}

class IncomeViewModelFactory(
    private val accountBookDatabaseDao: AccountBookDatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IncomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IncomeViewModel(accountBookDatabaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}