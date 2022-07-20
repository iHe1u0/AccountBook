package com.imorning.accountbook.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imorning.accountbook.utils.Const
import java.sql.Date

@Entity(tableName = Const.BALANCE_TABLE_NAME)
data class BalanceData(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    @ColumnInfo(name = "value", defaultValue = "0.0")
    val value: Double,

    // 时间
    @NonNull
    @ColumnInfo(name = "date")
    val date: Date,

    // 上次操作类型
    @ColumnInfo(name = "type")
    val type: String

)
