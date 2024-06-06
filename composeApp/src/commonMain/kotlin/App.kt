import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.models.PortfolioInfo
import domain.models.PortfolioStockInfo
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.PortfolioScreen
import ui.composables.BottomBar
import ui.theme.InvestAppTheme

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
@Preview
fun App() {
    InvestAppTheme {
        AdaptiveScaffold(bottomBar = {
            BottomBar()
        }) { innerPadding ->
            PortfolioScreen(
                modifier = Modifier.padding(innerPadding),
                portfolioInfo = portfolioInfo
            )
        }
    }
}


private val portfolioInfo = PortfolioInfo(
    totalValue = 1248099.60,
    totalProfit = -66959.35,
    totalProfitPercent = -5.36,
    stocks = listOf(
        PortfolioStockInfo(
            uid = "962e2a95-02a9-4171-abd7-aa198dbe643a",
            ticker = "GAZP",
            name = "Газпром",
            amount = 440,
            averagePrice = 159.14,
            lastPrice = 123.85,
            totalValue = 54494.00,
            profit = -15527.60,
            profitPercent = -22.18,
            logoUrl = "RU0007661625",
            backgroundColor = "#117cbf",
            textColor = "#ffffff"
        ),
        PortfolioStockInfo(
            uid = "de08affe-4fbd-454e-9fd1-46a81b23f870",
            ticker = "POSI",
            name = "Positive Technologies",
            amount = 80,
            averagePrice = 2371.53,
            lastPrice = 2972.20,
            totalValue = 237776.00,
            profit = 48053.60,
            profitPercent = 25.33,
            logoUrl = "RU000A103X66",
            backgroundColor = "#e52320",
            textColor = "#ffffff"
        ),
        PortfolioStockInfo(
            uid = "0da66728-6c30-44c4-9264-df8fac2467ee",
            ticker = "NVTK",
            name = "НОВАТЭК",
            amount = 185,
            averagePrice = 1453.71,
            lastPrice = 1039.00,
            totalValue = 192215.00,
            profit = -76721.35,
            profitPercent = -28.53,
            logoUrl = "novatec",
            backgroundColor = "#dde0e4",
            textColor = "#000000"
        ),
        PortfolioStockInfo(
            uid = "e6123145-9665-43e0-8413-cd61b8aa9b13",
            ticker = "SBER",
            name = "Сбер Банк",
            amount = 40,
            averagePrice = 320.39,
            lastPrice = 313.31,
            totalValue = 12532.40,
            profit = -283.20,
            profitPercent = -2.21,
            logoUrl = "sber3",
            backgroundColor = "#309c0b",
            textColor = "#ffffff"
        ),
        PortfolioStockInfo(
            uid = "c190ff1f-1447-4227-b543-316332699ca5",
            ticker = "SBERP",
            name = "Сбер Банк - привилегированные акции",
            amount = 520,
            averagePrice = 250.94,
            lastPrice = 314.06,
            totalValue = 163311.20,
            profit = 32822.40,
            profitPercent = 25.15,
            logoUrl = "sber3",
            backgroundColor = "#309c0b",
            textColor = "#ffffff"
        ),
        PortfolioStockInfo(
            uid = "459a1a0a-0253-465a-bd4e-afaaf5e670b0",
            ticker = "MBNK",
            name = "МТС-Банк",
            amount = 235,
            averagePrice = 2546.12,
            lastPrice = 2321.00,
            totalValue = 545435.00,
            profit = -52903.20,
            profitPercent = -8.84,
            logoUrl = "mtcbank",
            backgroundColor = "#E30611",
            textColor = "#ffffff"
        ),
        PortfolioStockInfo(
            uid = "509edd0c-129c-4ee2-934d-7f6246126da1",
            ticker = "GMKN",
            name = "Норильский никель",
            amount = 300,
            averagePrice = 149.12,
            lastPrice = 141.12,
            totalValue = 42336.00,
            profit = -2400.00,
            profitPercent = -5.36,
            logoUrl = "nornikel",
            backgroundColor = "#2c499a",
            textColor = "#ffffff"
        )
    )
)
