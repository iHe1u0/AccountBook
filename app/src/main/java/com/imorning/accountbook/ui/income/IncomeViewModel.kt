package com.imorning.accountbook.ui.income

import androidx.lifecycle.ViewModel
import com.imorning.accountbook.dao.BookDao
import com.imorning.accountbook.entity.Book
import kotlinx.coroutines.flow.Flow

class IncomeViewModel(private val bookDao: BookDao) : ViewModel() {
    fun getAll(): Flow<List<Book>> = bookDao.getAll()
}