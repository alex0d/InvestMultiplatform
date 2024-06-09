package utils

import androidx.compose.ui.graphics.Color

fun Color.Companion.fromHex(colorString: String) = Color(colorString.removePrefix("#").toLong(16) or 0x00000000FF000000)