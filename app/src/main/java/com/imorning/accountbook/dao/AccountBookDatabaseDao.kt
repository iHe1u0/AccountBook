package com.imorning.accountbook.dao

import androidx.room.*
import com.imorning.accountbook.entity.BalanceData
import com.imorning.accountbook.entity.ExpenseData
import com.imorning.accountbook.entity.IncomeData
import kotlinx.coroutines.flow.Flow

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//           "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

@Dao
interface AccountBookDatabaseDao {

    @Insert
    suspend fun insert(incomeData: IncomeData)

    @Insert
    suspend fun insert(expenseData: ExpenseData)

    @Insert
    suspend fun insert(balanceData: BalanceData)

    @Delete
    suspend fun delete(incomeData: IncomeData)

    @Delete
    suspend fun delete(expenseData: ExpenseData)

    @Delete
    suspend fun delete(balanceData: BalanceData)

    @Query("SELECT * FROM income")
    fun queryAllIncome(): Flow<List<IncomeData>>

    @Query("SELECT * FROM expense")
    fun queryAllDisburse(): Flow<List<ExpenseData>>

    @Query("SELECT * FROM balance")
    fun queryAllBalanceChange(): Flow<List<BalanceData>>

    @Query("SELECT * FROM income WHERE id IN (:id)")
    fun queryIncome(id: Int): Flow<IncomeData>

    @Query("SELECT * FROM expense WHERE id IN (:id)")
    fun queryDisburse(id: Int): Flow<ExpenseData>

    @Query("SELECT * FROM balance WHERE id IN (:id)")
    fun queryBalance(id: Int): Flow<BalanceData>

    @Query("SELECT * FROM income WHERE type LIKE :type")
    fun queryIncomeByType(type: String): Flow<List<IncomeData>>

    @Query("SELECT * FROM expense WHERE type LIKE :type")
    fun queryDisburseByType(type: String): Flow<List<ExpenseData>>

    @Query("SELECT * FROM balance WHERE type LIKE :type")
    fun queryBalanceByType(type: String): Flow<List<BalanceData>>

    @Query("SELECT sum FROM balance ORDER BY id DESC LIMIT 0,1")
    suspend fun queryLastBalance(): Double?

    @Query("SELECT type FROM income")
    fun queryIncomeType(): Flow<List<String>>

    @Query("SELECT type FROM expense")
    fun queryExpenseType(): Flow<List<String>>

    @Query("SELECT value FROM income")
    fun queryIncomeValue(): Flow<List<Double>>

    @Query("SELECT value FROM expense")
    fun queryExpenseValue(): Flow<List<Double>>

    @Update
    suspend fun update(incomeData: IncomeData)

    @Update
    suspend fun update(expenseData: ExpenseData)

    @Update
    suspend fun update(balanceData: BalanceData)
}