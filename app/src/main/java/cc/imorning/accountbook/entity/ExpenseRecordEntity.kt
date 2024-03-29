package cc.imorning.accountbook.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cc.imorning.accountbook.utils.Config
import java.sql.Date

@Entity(tableName = Config.EXPENSE_TABLE_NAME)
data class ExpenseRecordEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    @ColumnInfo(name = "value", defaultValue = "0.0")
    val value: Double,

    @NonNull
    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "remark")
    val remark: String?,
)
