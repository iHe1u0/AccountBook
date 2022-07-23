package com.imorning.accountbook.dao

import androidx.room.*
import com.imorning.accountbook.entity.BalanceRecordEntity
import com.imorning.accountbook.entity.ExpenseRecordEntity
import com.imorning.accountbook.entity.IncomeRecordEntity
import kotlinx.coroutines.flow.Flow

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//           "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

@Dao
interface AccountBookDatabaseDao {

    @Insert
    suspend fun insert(incomeRecordEntity: IncomeRecordEntity)

    @Insert
    suspend fun insert(expenseRecordEntity: ExpenseRecordEntity)

    @Insert
    suspend fun insert(balanceRecordEntity: BalanceRecordEntity)

    @Delete
    suspend fun delete(incomeRecordEntity: IncomeRecordEntity)

    @Delete
    suspend fun delete(expenseRecordEntity: ExpenseRecordEntity)

    @Delete
    suspend fun delete(balanceRecordEntity: BalanceRecordEntity)

    @Query("SELECT * FROM income")
    fun queryAllIncome(): Flow<List<IncomeRecordEntity>>

    @Query("SELECT * FROM expense")
    fun queryAllDisburse(): Flow<List<ExpenseRecordEntity>>

    @Query("SELECT * FROM balance")
    fun queryAllBalanceChange(): Flow<List<BalanceRecordEntity>>

    @Query("SELECT * FROM income WHERE id IN (:id)")
    fun queryIncome(id: Int): Flow<IncomeRecordEntity>

    @Query("SELECT * FROM expense WHERE id IN (:id)")
    fun queryDisburse(id: Int): Flow<ExpenseRecordEntity>

    @Query("SELECT * FROM balance WHERE id IN (:id)")
    fun queryBalance(id: Int): Flow<BalanceRecordEntity>

    @Query("SELECT * FROM income WHERE type LIKE :type")
    fun queryIncomeByType(type: String): Flow<List<IncomeRecordEntity>>

    @Query("SELECT * FROM expense WHERE type LIKE :type")
    fun queryDisburseByType(type: String): Flow<List<ExpenseRecordEntity>>

    @Query("SELECT * FROM balance WHERE type LIKE :type")
    fun queryBalanceByType(type: String): Flow<List<BalanceRecordEntity>>

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
    suspend fun update(incomeRecordEntity: IncomeRecordEntity)

    @Update
    suspend fun update(expenseRecordEntity: ExpenseRecordEntity)

    @Update
    suspend fun update(balanceRecordEntity: BalanceRecordEntity)
}