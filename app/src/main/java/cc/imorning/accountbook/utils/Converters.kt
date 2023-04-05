package cc.imorning.accountbook.utils

import androidx.room.TypeConverter
import java.sql.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toTimestamp(data: Date?): Long? {
        return data?.time?.toLong()
    }
}