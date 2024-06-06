package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import domain.models.PortfolioInfo
import domain.models.PortfolioStockInfo
import investmultiplatform.composeapp.generated.resources.Res
import investmultiplatform.composeapp.generated.resources.empty_portfolio_message
import investmultiplatform.composeapp.generated.resources.pieces_short
import investmultiplatform.composeapp.generated.resources.portfolio
import investmultiplatform.composeapp.generated.resources.sentiment_dissatisfied
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.composables.ProfitText
import utils.toCurrencyFormat

@Composable
fun PortfolioScreen(
    modifier: Modifier = Modifier,
    portfolioInfo: PortfolioInfo,
) {
    Column(
        modifier = modifier
    ) {
        TotalBalanceCard(
            portfolioInfo.totalValue,
            portfolioInfo.totalProfit,
            portfolioInfo.totalProfitPercent
        )
        if (portfolioInfo.stocks.isNotEmpty()) {
            LazyColumn {
                items(portfolioInfo.stocks) { stock ->
                    StockItem(stock, onClick = {
//                        navigator.navigate(StockDetailsScreenDestination(stockUid = stock.uid))
                    })
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.empty_portfolio_message),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(24.dp))
                Icon(
                    painterResource(Res.drawable.sentiment_dissatisfied),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.size(48.dp)
                )
            }

        }
    }
}

@Composable
private fun TotalBalanceCard(
    totalValue: Double,
    totalProfit: Double,
    totalProfitPercent: Double
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(Res.string.portfolio),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = totalValue.toCurrencyFormat("RUB"),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(4.dp))
            ProfitText(
                profit = totalProfit,
                profitPercent = totalProfitPercent
            )
        }
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
private fun StockItem(
    stock: PortfolioStockInfo,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                model = "https://invest-brands.cdn-tinkoff.ru/${stock.logoUrl}x160.png",
                contentDescription = "${stock.name} logo"
            )
            Spacer(Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stock.name,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = stock.totalValue.toCurrencyFormat("RUB"),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.End
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${stock.amount} ${stringResource(Res.string.pieces_short)} · ${stock.lastPrice.toCurrencyFormat("RUB")}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.width(2.dp))
                    ProfitText(
                        profit = stock.profit,
                        profitPercent = stock.profitPercent,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}
