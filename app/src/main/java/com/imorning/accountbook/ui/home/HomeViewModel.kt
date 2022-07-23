package com.imorning.accountbook.ui.home

import androidx.lifecycle.*
import com.imorning.accountbook.entity.BalanceRecordEntity
import com.imorning.accountbook.entity.ExpenseRecordEntity
import com.imorning.accountbook.entity.IncomeRecordEntity

private const val TAG = "HomeViewModel"

class HomeViewModel(private val repository: RecordRepository) : ViewModel() {

    val incomeLists:LiveData<List<IncomeRecordEntity>> =
        repository.incomeLists.asLiveData()
}

class HomeViewModelFactory(
    private val repository: RecordRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

class Records(
    public val incomeRecords: List<IncomeRecordEntity>,
    public val expenseRecords: List<ExpenseRecordEntity>,
    public val balanceRecords: List<BalanceRecordEntity>,
)