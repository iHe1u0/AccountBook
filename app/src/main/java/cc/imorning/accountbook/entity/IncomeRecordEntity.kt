package cc.imorning.accountbook.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cc.imorning.accountbook.utils.Config
import java.sql.Date

@Entity(tableName = Config.INCOME_TABLE_NAME)
data class IncomeRecordEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    @ColumnInfo(name = "value", defaultValue = "0.0")
    val value: Double,

    // 时间
    @NonNull
    @ColumnInfo(name = "date")
    val date: Date,

    // 类型
    @ColumnInfo(name = "type")
    val type: String,

    // 备注
    @ColumnInfo(name = "remark")
    val remark: String?,
)