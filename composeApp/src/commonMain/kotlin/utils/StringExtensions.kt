package utils

fun String.toIntOrZero(): Int {
    return this.toIntOrNull() ?: 0
}