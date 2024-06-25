package screens.profile

import MyIcons
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import investmultiplatform.composeapp.generated.resources.Res
import investmultiplatform.composeapp.generated.resources.error_occurred
import investmultiplatform.composeapp.generated.resources.logout
import myicons.AccountCircle
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import screens.auth.AuthScreen

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val navigator = LocalNavigator.current

    Column(
        modifier = modifier then Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is ProfileState.Error -> {
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

            is ProfileState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is ProfileState.Success -> {
                Icon(
                    modifier = Modifier
                        .size(240.dp)
                        .padding(top = 32.dp, bottom = 16.dp),
                    painter = rememberVectorPainter(MyIcons.AccountCircle),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = state.email,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${state.firstname} ${state.lastname ?: ""}".trimEnd(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.padding(bottom = 32.dp),
                    onClick = {
                        viewModel.logout()
                        navigator?.parent?.replaceAll(AuthScreen())
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.logout),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}