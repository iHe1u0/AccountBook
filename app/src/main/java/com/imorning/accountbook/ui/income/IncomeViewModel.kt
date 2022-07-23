package com.imorning.accountbook.ui.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.AccountBookDatabaseDao
import com.imorning.accountbook.entity.BalanceRecordEntity
import com.imorning.accountbook.entity.IncomeRecordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class IncomeViewModel(private val accountBookDatabaseDao: AccountBookDatabaseDao) : ViewModel() {

    fun insert(incomeRecordEntity: IncomeRecordEntity) {
        runBlocking {
            var oldSum: Double? = accountBookDatabaseDao.queryLastBalance()
            if (oldSum == null) {
                oldSum = 0.0
            }
            val balanceRecordEntity = BalanceRecordEntity(
                id = 0,
                date = incomeRecordEntity.date,
                value = incomeRecordEntity.value,
                type = incomeRecordEntity.type,
                sum = oldSum + incomeRecordEntity.value
            )
            accountBookDatabaseDao.insert(incomeRecordEntity = incomeRecordEntity)
            accountBookDatabaseDao.insert(balanceRecordEntity = balanceRecordEntity)
        }
    }

    fun delete(incomeRecordEntity: IncomeRecordEntity) {
        runBlocking {
            accountBookDatabaseDao.delete(incomeRecordEntity = incomeRecordEntity)
        }
    }

    fun queryAll(): Flow<List<IncomeRecordEntity>> {
        return accountBookDatabaseDao.queryAllIncome()
    }

    fun queryById(id: Int): Flow<IncomeRecordEntity> {
        return accountBookDatabaseDao.queryIncome(id)
    }

    fun queryByType(type: String): Flow<List<IncomeRecordEntity>> {
        return accountBookDatabaseDao.queryIncomeByType(type)
    }

    fun update(incomeRecordEntity: IncomeRecordEntity) {
        runBlocking {
            accountBookDatabaseDao.update(incomeRecordEntity = incomeRecordEntity)
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