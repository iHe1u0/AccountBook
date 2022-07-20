package com.imorning.accountbook.utils

object Const {
    enum class IsIncome {
        NO, YES
    }

    const val DATABASE_NAME = "account_books.db"

    @Deprecated(message = "use ${this.INCOME_TABLE_NAME} or ${this.DISBURSE_TABLE_NAME} or ${this.BALANCE_TABLE_NAME} instead.")
    const val TABLE_NAME = "book"

    const val INCOME_TABLE_NAME = "income"

    const val DISBURSE_TABLE_NAME = "disburse"

    const val BALANCE_TABLE_NAME = "balance"


}