package dev.tabansi.palm.palmapp.ui.settings

import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
}

data class SettingsUiState(
    val darkTheme: Boolean
)