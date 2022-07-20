package com.imorning.accountbook.ui.disburse

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.DatabaseDao
import com.imorning.accountbook.entity.BalanceData
import com.imorning.accountbook.entity.DisburseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class DisburseViewModel(private val databaseDao: DatabaseDao) : ViewModel() {

    fun insert(disburseData: DisburseData) {
        runBlocking {
            var oldSum: Double? = databaseDao.queryLastBalance()
            if (oldSum == null) {
                oldSum = 0.0
            }
            val balanceData = BalanceData(
                id = 0,
                date = disburseData.date,
                value = disburseData.value,
                type = disburseData.type,
                sum = oldSum - disburseData.value
            )
            databaseDao.insert(disburseData = disburseData)
            databaseDao.insert(balanceData = balanceData)
        }
    }

    fun delete(disburseData: DisburseData) {
        runBlocking {
            databaseDao.delete(disburseData = disburseData)
        }
    }

    fun queryAll(): Flow<List<DisburseData>> {
        return databaseDao.queryAllDisburse()
    }

    fun update(disburseData: DisburseData) {
        runBlocking {
            databaseDao.update(disburseData = disburseData)
        }
    }

    companion object {
        private const val TAG = "DisburseViewModel"
    }
}

class DisburseViewModelFactory(
    private val databaseDao: DatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisburseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DisburseViewModel(databaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}