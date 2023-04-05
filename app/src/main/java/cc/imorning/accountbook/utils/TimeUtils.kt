package cc.imorning.accountbook.utils

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    fun dateFormatter(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(date)
    }
}