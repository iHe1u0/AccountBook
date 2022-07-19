package com.imorning.accountbook.dao

import androidx.room.*
import com.imorning.accountbook.entity.Book
import kotlinx.coroutines.flow.Flow

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//           "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User
@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAll(): Flow<List<Book>>

    @Query("SELECT * FROM book WHERE income <> 0.0")
    fun getAllIncome(): Flow<List<Book>>

    @Query("SELECT * FROM book WHERE id IN (:id)")
    fun findByID(id: Int): Flow<List<Book>>

    @Query("SELECT * FROM book WHERE type LIKE :type")
    fun findByType(type: String): Flow<List<Book>>

    @Query("SELECT * FROM book WHERE is_income = 1")
    fun queryIncome(): Flow<List<Book>>

    @Query("SELECT * FROM book WHERE is_income = 0")
    fun queryDisburse(): Flow<List<Book>>

    @Insert
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Update
    fun update(book: Book)
}