package ui.composables

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import utils.toCurrencyFormat
import utils.toDecimalFormat
import kotlin.math.absoluteValue

@Composable
fun ProfitText(profit: Double, profitPercent: Double, style: TextStyle = LocalTextStyle.current, textAlign: TextAlign? = null) {
    val formattedProfit = profit.toCurrencyFormat("RUB")
    val formattedProfitPercent = profitPercent.absoluteValue.toDecimalFormat()

    return when {
        profit > 0 -> {
            Text(
                text = "+$formattedProfit ($formattedProfitPercent%)",
                color = Color.Green,
                style = style,
                textAlign = textAlign
            )
        }
        profit < 0 -> {
            Text(
                text = "$formattedProfit ($formattedProfitPercent%)",
                color = Color.Red,
                style = style,
                textAlign = textAlign
            )
        }
        else -> {
            Text(
                text = "$formattedProfit ($formattedProfitPercent%)",
                style = style,
                textAlign = textAlign
            )
        }
    }
}