package screens.order

import MyIcons
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import domain.models.Share
import investmultiplatform.composeapp.generated.resources.*
import kotlinx.coroutines.launch
import myicons.Minus
import myicons.Plus
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import ui.composables.ShareItem
import utils.toCurrencyFormat
import utils.toIntOrZero

data class OrderScreen(
    val orderAction: OrderAction,
    val stockUid: String = ""
) : Screen {
    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content(
//    navigator: ResultBackNavigator<Boolean>,
    ) {
        val coroutineScope = rememberCoroutineScope()
        val navigator = LocalNavigator.current
        val viewModel: OrderViewModel = koinViewModel { parametersOf(orderAction, stockUid) }

        val state = viewModel.state.collectAsState().value
        val availableLots = viewModel.availableLots.collectAsState().value
        val lotsInput = viewModel.lotsInput.collectAsState().value
        val totalValue = viewModel.totalValue.collectAsState().value

        Scaffold(
            topBar = {
                OrderTopAppBar(
                    onNavigationIconClick = {
                        navigator?.pop()
                    },
                    orderAction = orderAction
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 2.dp)
            ) {
                when (state) {
                    is OrderDetailsState.OrderDetailsFetched -> {
                        val share = state.share
                        OrderOnDetailsFetched(
                            share = share,
                            availableLots = availableLots,
                            lotsInput = lotsInput,
                            totalValue = totalValue,
                            orderAction = orderAction,
                            onUpdateInputLots = viewModel::updateLotsInput,
                            onDecreaseLots = viewModel::decreaseLots,
                            onIncreaseLots = viewModel::increaseLots,
                            onConfirmOrder = {
                            coroutineScope.launch {
                                viewModel.confirmOrder()
                                navigator?.pop()
                            }
                            },
                        )
                    }
                    is OrderDetailsState.Loading -> OrderOnLoading()
                    is OrderDetailsState.Error -> OrderOnError()
                }
            }
        }
    }

}

@Composable
internal fun OrderOnDetailsFetched(
    share: Share,
    availableLots: Int,
    lotsInput: String,
    totalValue: String,
    orderAction: OrderAction,
    onUpdateInputLots: (String) -> Unit,
    onDecreaseLots: () -> Unit,
    onIncreaseLots: () -> Unit,
    onConfirmOrder: () -> Unit,
) {
    var inTransaction by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ShareItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            share = share
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = share.lastPrice.toCurrencyFormat("RUB"),
            readOnly = true,
            onValueChange = {},
            colors = TextFieldDefaults.colors(
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
        )
        Spacer(modifier = Modifier.height(28.dp))
        LotsSection(
            lotsInput = lotsInput,
            onUpdateInputLots = onUpdateInputLots,
            onDecreaseLots = onDecreaseLots,
            onIncreaseLots = onIncreaseLots,
            orderAction = orderAction,
            availableLots = availableLots
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            if (orderAction == OrderAction.SELL) {
                Text(stringResource(Res.string.available_lots, availableLots), color = Color.Gray)
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(stringResource(Res.string.pcs_in_lot, share.lot), color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (lotsInput.toIntOrZero() > 0) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(Res.string.total_value), color = Color.Gray)
                Text(totalValue, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = !inTransaction && lotsInput.toIntOrZero() > 0,
            onClick = {
                inTransaction = true
                onConfirmOrder()
            },
        ) {
            if (!inTransaction) {
                Text(
                    text = if (orderAction == OrderAction.BUY) stringResource(Res.string.buy) else stringResource(
                        Res.string.sell
                    ),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 18.sp
                    )
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
private fun LotsSection(
    lotsInput: String,
    onUpdateInputLots: (String) -> Unit,
    onDecreaseLots: () -> Unit,
    onIncreaseLots: () -> Unit,
    orderAction: OrderAction,
    availableLots: Int
) {
    val lotsInt = lotsInput.toIntOrZero()

    Text(stringResource(Res.string.lots_amount), color = Color.Gray)
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = lotsInput,
        onValueChange = { onUpdateInputLots(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
        trailingIcon = {
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onDecreaseLots,
                    enabled = lotsInt > 0
                ) {
                    Icon(
                        rememberVectorPainter(MyIcons.Minus),
                        modifier = Modifier.size(24.dp),
                        contentDescription = "-",
                        tint = if (lotsInt > 0) {
                            if (isSystemInDarkTheme()) Color.White else Color.DarkGray
                        } else {
                            if (isSystemInDarkTheme()) Color.Gray else Color.LightGray
                        }
                    )
                }
                IconButton(
                    onClick = onIncreaseLots,
                    enabled = orderAction == OrderAction.BUY || lotsInt < availableLots
                ) {
                    Icon(
                        painter = rememberVectorPainter(MyIcons.Plus),
                        modifier = Modifier.size(24.dp),
                        contentDescription = "+",
                        tint = if (orderAction == OrderAction.BUY || lotsInt < availableLots) {
                            if (isSystemInDarkTheme()) Color.White else Color.DarkGray
                        } else {
                            if (isSystemInDarkTheme()) Color.Gray else Color.LightGray
                        }
                    )
                }
            }
        },
    )
}

@Composable
private fun OrderOnLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun OrderOnError() {
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun OrderTopAppBar(
    onNavigationIconClick: () -> Unit,
    orderAction: OrderAction
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(Res.string.go_back)
                )
            }
        },
        title = {
            if (orderAction == OrderAction.BUY)
                Text(
                    text = stringResource(Res.string.buying),
                    style = MaterialTheme.typography.titleLarge
                )
            else
                Text(
                    text = stringResource(Res.string.selling),
                    style = MaterialTheme.typography.titleLarge
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )
}

@Preview
@Composable
private fun OrderOnDetailsFetchedPreview() {
    OrderOnDetailsFetched(
        share = Share(
            uid = "64c0da45-4c90-41d4-b053-0c66c7a8ddcd",
            figi = "TCS109029557",
            ticker = "SBERP",
            classCode = "SPEQ",
            isin = "RU0009029557",
            currency = "rub",
            name = "Сбер Банк - привилегированные акции",
            countryOfRisk = "RU",
            countryOfRiskName = "Российская Федерация",
            sector = "financial",
            lot = 1,
            lastPrice = 295.94,
            url = "sber3",
            backgroundColor = "#309c0b",
            textColor = "#ffffff"
        ),
        availableLots = 10,
        lotsInput = "5",
        totalValue = 14797.0.toCurrencyFormat("RUB"),
        orderAction = OrderAction.SELL,
        onUpdateInputLots = {},
        onDecreaseLots = {},
        onIncreaseLots = {},
        onConfirmOrder = {}
    )
}