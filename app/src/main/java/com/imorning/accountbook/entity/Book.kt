package com.imorning.accountbook.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imorning.accountbook.utils.Const

/**
 * 账本 数据库
 */
@Entity(tableName = Const.TABLE_NAME)
data class Book(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // 时间
    @NonNull
    @ColumnInfo(name = "time")
    val time: Long,

    // 收入
    @ColumnInfo(name = "income")
    val income: Double,

    // 支出
    @ColumnInfo(name = "disburse")
    val disburse: Double,

    // 余额
    @ColumnInfo(name = "balance")
    val balance: Double,

    // 是否为收入
    @ColumnInfo(name = "is_income")
    val isIncome: Boolean,

    // 类型
    @ColumnInfo(name = "type")
    val type: String,

    // 备注
    @ColumnInfo(name = "remark")
    val remark: String?
)