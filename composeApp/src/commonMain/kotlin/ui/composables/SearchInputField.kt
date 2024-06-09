package ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import investmultiplatform.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import screens.search.SearchViewModel

@Composable
fun SearchInputField(
    searchFieldState: SearchViewModel.SearchFieldState,
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
    onClearInputClicked: () -> Unit,
    onClicked: () -> Unit,
    onChevronClicked: () -> Unit,
    onKeyboardHidden: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val isKeyboardOpen by keyboardAsState()

    if (searchFieldState is SearchViewModel.SearchFieldState.WithInputActive && isKeyboardOpen) {
        onKeyboardHidden()
    }

    TextField(
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            onKeyboardHidden()
        }, onGo = {
            onKeyboardHidden()
        }),
        value = inputText,
        onValueChange = { newInput -> onSearchInputChanged(newInput) },
        leadingIcon = {
            when (searchFieldState) {
                SearchViewModel.SearchFieldState.Idle -> Icon(
                    painter = painterResource(Res.drawable.navbar_icons_search_inactive),
                    contentDescription = "Search icon",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )

                SearchViewModel.SearchFieldState.EmptyActive,
                SearchViewModel.SearchFieldState.WithInputActive -> Icon(
                    painter = painterResource(Res.drawable.ic_chevron_left),
                    contentDescription = "Search chevron icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                    modifier = Modifier.clickable { onChevronClicked.invoke() }
                )
            }
        },
        colors = when (searchFieldState) {
            SearchViewModel.SearchFieldState.Idle -> searchFieldColorsStateIdle()
            SearchViewModel.SearchFieldState.EmptyActive,
            SearchViewModel.SearchFieldState.WithInputActive -> searchFieldColorsStateActive()
        },
        trailingIcon = if (searchFieldState is SearchViewModel.SearchFieldState.WithInputActive) {
            {
                Icon(
                    painter = painterResource(Res.drawable.ic_close_search),
                    contentDescription = "Search close icon",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clickable { onClearInputClicked.invoke() }
                )
            }
        } else {
            null
        },
        placeholder = {
            Text(
                text = stringResource(Res.string.search_stock_placeholder),
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                ),
                textAlign = TextAlign.Start,
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .focusRequester(focusRequester)
            .focusable(true),
        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
            LaunchedEffect(key1 = interactionSource) {
                interactionSource.interactions.collect { interaction ->
                    if (interaction is PressInteraction.Release) {
                        onClicked.invoke()
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun searchFieldColorsStateIdle() = TextFieldDefaults.textFieldColors(
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
    cursorColor = Color.Transparent,
    focusedLabelColor = Color.Transparent,
    unfocusedLabelColor = Color.Transparent,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun searchFieldColorsStateActive() = TextFieldDefaults.textFieldColors(
    containerColor = MaterialTheme.colorScheme.secondaryContainer,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    disabledTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
    focusedLabelColor = Color.Transparent,
    unfocusedLabelColor = Color.Transparent,
)

@Composable
private fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}