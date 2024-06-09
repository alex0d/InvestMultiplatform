package screens.portfolio

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import coil3.compose.AsyncImage
import domain.models.PortfolioInfo
import domain.models.PortfolioStockInfo
import investmultiplatform.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.composables.ProfitText
import utils.previewproviders.FakePortfolioInfo
import utils.toCurrencyFormat

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PortfolioScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: PortfolioViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState().value

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> viewModel.startUpdating()
                Lifecycle.Event.ON_STOP -> viewModel.stopUpdating()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
    ) {
        when (state) {
            is PortfolioState.Loading -> PortfolioScreenOnLoading()
            is PortfolioState.PortfolioInfoFetched -> PortfolioScreenOnInfoFetched(
                portfolioInfo = state.portfolioInfo,
//                navigator = navigator
            )
            is PortfolioState.Error -> PortfolioScreenOnError()
        }
    }
}

@Preview
@Composable
private fun PortfolioScreenOnInfoFetched(
    @PreviewParameter(FakePortfolioInfo::class) portfolioInfo: PortfolioInfo,
//    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {
    Column {
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

@Composable
private fun PortfolioScreenOnLoading() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")

    val topCardColor by infiniteTransition.animateColor(
        initialValue = MaterialTheme.colorScheme.primaryContainer,
        targetValue = MaterialTheme.colorScheme.secondaryContainer,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "topCardColor"
    )

    val itemsColor by infiniteTransition.animateColor(
        initialValue = MaterialTheme.colorScheme.surfaceVariant,
        targetValue = MaterialTheme.colorScheme.secondaryContainer,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "itemsColor"
    )

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                .height(128.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = topCardColor,
            )
        ) { }
        Spacer(Modifier.height(8.dp))
        Column {
            repeat(5) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(68.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = itemsColor
                    )
                ) { }
            }
        }
    }
}

@Composable
private fun PortfolioScreenOnError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.error_occurred),
            textAlign = TextAlign.Center,
        )
    }
}
