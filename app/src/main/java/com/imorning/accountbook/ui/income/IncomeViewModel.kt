package com.imorning.accountbook.ui.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.DatabaseDao
import com.imorning.accountbook.entity.BalanceData
import com.imorning.accountbook.entity.IncomeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class IncomeViewModel(private val databaseDao: DatabaseDao) : ViewModel() {

    fun insert(incomeData: IncomeData) {
        runBlocking {
            var oldSum: Double? = databaseDao.queryLastBalance()
            if (oldSum == null) {
                oldSum = 0.0
            }
            val balanceData = BalanceData(
                id = 0,
                date = incomeData.date,
                value = incomeData.value,
                type = incomeData.type,
                sum = oldSum - incomeData.value
            )
            databaseDao.insert(incomeData = incomeData)
            databaseDao.insert(balanceData = balanceData)
        }
    }

    fun delete(incomeData: IncomeData) {
        runBlocking {
            databaseDao.delete(incomeData = incomeData)
        }
    }

    fun queryAll(): Flow<List<IncomeData>> {
        return databaseDao.queryAllIncome()
    }

    fun queryById(id: Int): Flow<IncomeData> {
        return databaseDao.queryIncome(id)
    }

    fun queryByType(type: String): Flow<List<IncomeData>> {
        return databaseDao.queryIncomeByType(type)
    }

    fun update(incomeData: IncomeData) {
        runBlocking {
            databaseDao.update(incomeData = incomeData)
        }
    }
}

class IncomeViewModelFactory(
    private val databaseDao: DatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IncomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IncomeViewModel(databaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}