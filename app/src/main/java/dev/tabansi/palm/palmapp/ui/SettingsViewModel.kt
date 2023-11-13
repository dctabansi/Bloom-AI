package dev.tabansi.palm.palmapp.ui

import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
}

data class SettingsUiState(
    val darkTheme: Boolean
)