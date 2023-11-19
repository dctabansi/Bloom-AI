package dev.tabansi.palm.palmapp.ui.chat

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tabansi.palm.palmapp.ui.AppViewModelProvider
import dev.tabansi.palm.palmapp.ui.PalmDestination

object ChatDestination : PalmDestination {
    override val route: String = "chat"
    const val chatIdArg: String = "chatId"
    val routeWithArgs: String = "$route/{$chatIdArg}"
}

@Composable
fun ChatScreen(
    navigateToChat: (Int) -> Unit,
    navigateUp: () -> Unit,
    viewModel: ChatViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

}