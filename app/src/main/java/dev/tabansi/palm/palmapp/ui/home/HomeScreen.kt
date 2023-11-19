package dev.tabansi.palm.palmapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tabansi.palm.palmapp.ui.AppViewModelProvider
import dev.tabansi.palm.palmapp.ui.PalmDestination

object HomeDestination : PalmDestination {
    override val route: String = "home"
}

@Composable
fun HomeScreen(
    navigateToChat: () -> Unit,
    navigateToSettings: () -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState = homeViewModel.uiState
}