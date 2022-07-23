package com.imorning.accountbook.ui.home

import com.imorning.accountbook.dao.AccountBookDatabaseDao
import com.imorning.accountbook.entity.ExpenseRecordEntity
import com.imorning.accountbook.entity.IncomeRecordEntity
import kotlinx.coroutines.flow.Flow

class RecordRepository(private val databaseDao: AccountBookDatabaseDao) {

    val incomeType: Flow<List<String>> = databaseDao.queryIncomeType()

    val incomeValue: Flow<List<Double>> = databaseDao.queryIncomeValue()

    val expenseType: Flow<List<String>> = databaseDao.queryExpenseType()

    val expenseValue: Flow<List<Double>> = databaseDao.queryExpenseValue()

    val incomeLists: Flow<List<IncomeRecordEntity>> = databaseDao.queryAllIncome()

}

class HomeRecordHelper(
    private val incomeLists: List<IncomeRecordEntity>,
    private val expenseLists: List<ExpenseRecordEntity>
) {

    fun getIncomeCategories(): HashMap<String, Double> {
        val list: HashMap<String, Double> = HashMap(1)
        if (incomeLists.isEmpty()) {
            list[""] = 0.0
            return list
        }
        incomeLists.map { item ->
            val itemKey = item.type
            val itemValue = item.value

            if (list.contains(itemKey)) {
                list[itemKey]?.let { list.put(itemKey, it.plus(itemValue)) }
            } else {
                list.put(itemKey, itemValue)
            }
        }
        return list
    }


    fun getExpenseCategories(): HashMap<String, Double> {
        val list: HashMap<String, Double> = HashMap(1)
        if (expenseLists.isEmpty()) {
            list[""] = 0.0
            return list
        }
        expenseLists.map { item ->
            val itemKey = item.type
            val itemValue = item.value

            if (list.contains(itemKey)) {
                list[itemKey]?.let { list.put(itemKey, it.plus(itemValue)) }
            } else {
                list.put(itemKey, itemValue)
            }
        }
        return list
    }


}

open class BaseRecord(
    val _category: String,
    val _value: Double
) {

    override fun equals(other: Any?): Boolean {
        if (other is BaseRecord) {
            return other._category.isNotEmpty() && _category == other._category
        }
        return false
    }

    override fun hashCode(): Int {
        return _category.hashCode()
    }
}