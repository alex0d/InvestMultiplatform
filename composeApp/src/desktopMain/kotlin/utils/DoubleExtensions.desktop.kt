package utils

import java.text.NumberFormat
import java.util.Currency

internal actual fun Double.toCurrencyFormat(currencyCode: String): String {
    val format = NumberFormat.getCurrencyInstance().apply {
        maximumFractionDigits = 2
        currency = Currency.getInstance(currencyCode)
    }
    return format.format(this)
}

internal actual fun Double.toDecimalFormat(): String {
    val format = NumberFormat.getInstance().apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return format.format(this)
}