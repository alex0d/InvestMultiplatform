package screens.tarot

import MyIcons
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import domain.models.TarotCard
import investmultiplatform.composeapp.generated.resources.*
import kotlinx.coroutines.delay
import myicons.Refresh
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import ui.composables.TwoButtonsAlertDialog

data class TarotScreen(
    val stockName: String
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel: TarotViewModel = koinViewModel { parametersOf(stockName) }
        val state = viewModel.state.collectAsState().value

        var openAlertDialog by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                if (state is TarotPredictionState.Success) {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = { navigator?.pop() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    tint = MaterialTheme.colorScheme.primary,
                                    contentDescription = stringResource(Res.string.go_back)
                                )
                            }
                        },
                        title = {
                            Text(
                                text = stringResource(Res.string.esoteric_analysis),
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        actions = {
                            IconButton(onClick = { openAlertDialog = true }) {
                                Icon(
                                    painter = rememberVectorPainter(MyIcons.Refresh),
                                    contentDescription = stringResource(Res.string.refresh),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    )
                }
            }
        ) { padding ->
            Box(modifier = Modifier
                .padding(top = padding.calculateTopPadding())
                .padding(horizontal = 2.dp)
            ) {
                when (state) {
                    is TarotPredictionState.Loading -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                    is TarotPredictionState.Error -> Box(
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
                    is TarotPredictionState.Success -> TarotPredictionOnSuccess(stockName, state)
                }

                if (openAlertDialog) {
                    TwoButtonsAlertDialog(
                        onDismissRequest = { openAlertDialog = false },
                        onConfirmation = {
                            openAlertDialog = false
                            viewModel.refreshTarotPrediction()
                        },
                        dialogTitle = stringResource(Res.string.updating_prediction_title),
                        dialogText = stringResource(Res.string.updating_prediction_text),
                        icon = Icons.Default.Warning
                    )
                }
            }
        }
    }

}

@Composable
private fun TarotPredictionOnSuccess(
    stockName: String,
    state: TarotPredictionState.Success
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if (!visible) {
            delay(150)
            visible = true
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = visible) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 20.dp),
                        text = stockName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            lineBreak = LineBreak.Paragraph,
                        ),
                        lineHeight = 28.sp,
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                    )
                    Image(
                        modifier = Modifier.fillParentMaxWidth(0.7f).clip(RoundedCornerShape(16.dp)),
                        painter = painterResource(getTarotImage(state.prediction.card)),
                        contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        text = stringResource(getTarotCardName(state.prediction.card)),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 24.sp,
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = state.prediction.prediction,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    Text(
                        text = stringResource(Res.string.not_iir_disclaimer),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

private fun getTarotImage(card: TarotCard): DrawableResource {
    return when (card) {
        TarotCard.THE_FOOL -> Res.drawable.tarot_the_fool
        TarotCard.THE_MAGICIAN -> Res.drawable.tarot_the_magician
        TarotCard.THE_HIGH_PRIESTESS -> Res.drawable.tarot_the_high_priestess
        TarotCard.THE_EMPRESS -> Res.drawable.tarot_the_empress
        TarotCard.THE_EMPEROR -> Res.drawable.tarot_the_emperor
        TarotCard.THE_HIEROPHANT -> Res.drawable.tarot_the_hierophant
        TarotCard.THE_LOVERS -> Res.drawable.tarot_the_lovers
        TarotCard.THE_CHARIOT -> Res.drawable.tarot_the_chariot
        TarotCard.JUSTICE -> Res.drawable.tarot_justice
        TarotCard.THE_HERMIT -> Res.drawable.tarot_the_hermit
        TarotCard.WHEEL_OF_FORTUNE -> Res.drawable.tarot_wheel_of_fortune
        TarotCard.STRENGTH -> Res.drawable.tarot_strength
        TarotCard.THE_HANGED_MAN -> Res.drawable.tarot_the_hanged_man
        TarotCard.DEATH -> Res.drawable.tarot_death
        TarotCard.TEMPERANCE -> Res.drawable.tarot_temperance
        TarotCard.THE_DEVIL -> Res.drawable.tarot_the_devil
        TarotCard.THE_TOWER -> Res.drawable.tarot_the_tower
        TarotCard.THE_MOON -> Res.drawable.tarot_the_moon
        TarotCard.THE_SUN -> Res.drawable.tarot_the_sun
        else -> throw IllegalArgumentException("Not implemented card: $card")
    }
}

private fun getTarotCardName(card: TarotCard): StringResource {
    return when (card) {
        TarotCard.THE_FOOL -> Res.string.the_fool
        TarotCard.THE_MAGICIAN -> Res.string.the_magician
        TarotCard.THE_HIGH_PRIESTESS -> Res.string.the_high_priestess
        TarotCard.THE_EMPRESS -> Res.string.the_empress
        TarotCard.THE_EMPEROR -> Res.string.the_emperor
        TarotCard.THE_HIEROPHANT -> Res.string.the_hierophant
        TarotCard.THE_LOVERS -> Res.string.the_lovers
        TarotCard.THE_CHARIOT -> Res.string.the_chariot
        TarotCard.JUSTICE -> Res.string.justice
        TarotCard.THE_HERMIT -> Res.string.the_hermit
        TarotCard.WHEEL_OF_FORTUNE -> Res.string.wheel_of_fortune
        TarotCard.STRENGTH -> Res.string.strength
        TarotCard.THE_HANGED_MAN -> Res.string.the_hanged_man
        TarotCard.DEATH -> Res.string.death
        TarotCard.TEMPERANCE -> Res.string.temperance
        TarotCard.THE_DEVIL -> Res.string.the_devil
        TarotCard.THE_TOWER -> Res.string.the_tower
        TarotCard.THE_MOON -> Res.string.the_moon
        TarotCard.THE_SUN -> Res.string.the_sun
        else -> throw IllegalArgumentException("Not implemented card: $card")
    }
}