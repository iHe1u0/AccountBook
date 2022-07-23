package com.imorning.accountbook.ui.home

import androidx.lifecycle.*

private const val TAG = "HomeViewModel"

class HomeViewModel(private val repository: RecordRepository) : ViewModel() {

    val incomeValues: LiveData<List<Double>> =
        repository.incomeValue.asLiveData()

    val incomeCategories: LiveData<List<String>> =
        repository.incomeType.asLiveData()

    val expenseValues: LiveData<List<Double>> =
        repository.incomeValue.asLiveData()

    val expenseCategories: LiveData<List<String>> =
        repository.incomeType.asLiveData()

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