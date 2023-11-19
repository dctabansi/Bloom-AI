package dev.tabansi.palm.palmapp.ui.settings

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tabansi.palm.palmapp.ui.AppViewModelProvider
import dev.tabansi.palm.palmapp.ui.PalmDestination

object SettingsDestination : PalmDestination {
    override val route: String = "settings"
}

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
}