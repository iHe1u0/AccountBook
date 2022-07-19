package com.imorning.accountbook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imorning.accountbook.dao.BookDao
import com.imorning.accountbook.entity.Book
import kotlinx.coroutines.flow.Flow

class DatabaseViewModel(private val bookDao: BookDao) : ViewModel() {

    fun getAll(): Flow<List<Book>> {
        return bookDao.getAll()
    }

    fun insert(book: Book) {
        bookDao.insert(book = book)
    }
}

class DatabaseViewModelFactory(
    private val bookDao: BookDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DatabaseViewModel(bookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}