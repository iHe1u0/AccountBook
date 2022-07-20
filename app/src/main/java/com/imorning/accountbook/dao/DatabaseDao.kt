package com.imorning.accountbook.dao

import androidx.room.*
import com.imorning.accountbook.entity.BalanceData
import com.imorning.accountbook.entity.DisburseData
import com.imorning.accountbook.entity.IncomeData
import kotlinx.coroutines.flow.Flow

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//           "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User


@Dao
interface DatabaseDao {

    @Insert
    suspend fun insert(incomeData: IncomeData)

    @Insert
    suspend fun insert(disburseData: DisburseData)

    @Insert
    suspend fun insert(balanceData: BalanceData)

    @Delete
    suspend fun delete(incomeData: IncomeData)

    @Delete
    suspend fun delete(disburseData: DisburseData)

    @Delete
    suspend fun delete(balanceData: BalanceData)

    @Query("SELECT * FROM income")
    fun queryAllIncome(): Flow<List<IncomeData>>

    @Query("SELECT * FROM disburse")
    fun queryAllDisburse(): Flow<List<DisburseData>>

    @Query("SELECT * FROM balance")
    fun queryAllBalanceChange(): Flow<List<BalanceData>>

    @Query("SELECT * FROM income WHERE id IN (:id)")
    fun queryIncome(id: Int): Flow<IncomeData>

    @Query("SELECT * FROM disburse WHERE id IN (:id)")
    fun queryDisburse(id: Int): Flow<DisburseData>

    @Query("SELECT * FROM balance WHERE id IN (:id)")
    fun queryBalance(id: Int): Flow<BalanceData>

    @Query("SELECT * FROM income WHERE type LIKE :type")
    fun queryIncomeByType(type: String): Flow<List<IncomeData>>

    @Query("SELECT * FROM disburse WHERE type LIKE :type")
    fun queryDisburseByType(type: String): Flow<List<DisburseData>>

    @Query("SELECT * FROM balance WHERE type LIKE :type")
    fun queryBalanceByType(type: String): Flow<List<BalanceData>>

    @Query("SELECT sum FROM balance ORDER BY id DESC LIMIT 0,1")
    suspend fun queryLastBalance(): Double?

    @Update
    suspend fun update(incomeData: IncomeData)

    @Update
    suspend fun update(disburseData: DisburseData)

    @Update
    suspend fun update(balanceData: BalanceData)
}