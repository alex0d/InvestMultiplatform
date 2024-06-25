package screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import domain.models.Share
import investmultiplatform.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import screens.main.BottomBarTab
import screens.stock.StockDetailsScreen
import ui.composables.SearchInputField
import ui.composables.ShareItem

@OptIn(KoinExperimentalAPI::class, InternalVoyagerApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val localFocusManager = LocalFocusManager.current

    val viewState = viewModel.viewState.collectAsState().value
    val searchFieldState = viewModel.searchFieldState.collectAsState().value
    val inputText = viewModel.inputText.collectAsState().value

    val keyboardController = LocalSoftwareKeyboardController.current
    val navigator = LocalNavigator.current

    SearchScreenLayout(
        modifier = modifier then Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
        viewState = viewState,
        searchFieldState = searchFieldState,
        inputText = inputText,
        onSearchInputChanged = { input -> viewModel.updateInput(input) },
        onSearchInputClicked = { viewModel.searchFieldActivated() },
        onClearInputClicked = { viewModel.clearInput() },
        onChevronClicked = {
            viewModel.revertToInitialState()
            keyboardController?.hide()
        },
        onKeyboardHidden = { viewModel.keyboardHidden() },
        onItemClicked = {
            navigator?.push(StockDetailsScreen(stockUid = it.uid))
        }
    )

    val tabNavigator = LocalTabNavigator.current
    BackHandler(true) {
        tabNavigator.current = BottomBarTab.Portfolio
    }
}

@Composable
private fun SearchScreenLayout(
    modifier: Modifier,
    viewState: SearchViewModel.ViewState,
    searchFieldState: SearchViewModel.SearchFieldState,
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchInputClicked: () -> Unit,
    onClearInputClicked: () -> Unit,
    onChevronClicked: () -> Unit,
    onKeyboardHidden: () -> Unit,
    onItemClicked: (Share) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        SearchHeader(searchFieldState = searchFieldState)
        SearchInputField(
            searchFieldState = searchFieldState,
            inputText = inputText,
            onClearInputClicked = onClearInputClicked,
            onSearchInputChanged = onSearchInputChanged,
            onClicked = onSearchInputClicked,
            onChevronClicked = onChevronClicked,
            onKeyboardHidden = onKeyboardHidden,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
        )
        when (viewState) {
            SearchViewModel.ViewState.IdleScreen -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(Res.string.start_investing),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            SearchViewModel.ViewState.Error -> {
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

            SearchViewModel.ViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            SearchViewModel.ViewState.NoResults -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(Res.string.no_results),
                        textAlign = TextAlign.Center,
                    )
                }
            }

            is SearchViewModel.ViewState.SearchResultsFetched -> {
                SearchResultsList(items = viewState.results, onItemClicked = onItemClicked)
            }
        }
    }
}

@Composable
private fun SearchHeader(
    searchFieldState: SearchViewModel.SearchFieldState
) {
    AnimatedVisibility(visible = searchFieldState == SearchViewModel.SearchFieldState.Idle) {
        Text(
            text = stringResource(Res.string.stock_search),
            modifier = Modifier.padding(start = 24.dp, end = 16.dp, bottom = 8.dp, top = 16.dp),
            style = TextStyle(
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
            )
        )
    }
}

@Composable
private fun SearchResultsList(items: List<Share>, onItemClicked: (Share) -> Unit) {
    LazyColumn {
        itemsIndexed(items = items) { index, share ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier.height(height = if (index == 0) 16.dp else 4.dp)
                )
                ShareItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    share = share,
                    onClick = onItemClicked
                )
            }
        }
    }
}
