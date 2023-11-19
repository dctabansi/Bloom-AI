package dev.tabansi.palm.palmapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.tabansi.palm.palmapp.ui.chat.ChatDestination
import dev.tabansi.palm.palmapp.ui.chat.ChatScreen
import dev.tabansi.palm.palmapp.ui.home.HomeDestination
import dev.tabansi.palm.palmapp.ui.home.HomeScreen
import dev.tabansi.palm.palmapp.ui.settings.SettingsDestination
import dev.tabansi.palm.palmapp.ui.settings.SettingsScreen

interface PalmDestination {
    val route: String
}

@Composable
fun PalmNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToChat = { navController.navigate("${ChatDestination.route}/${it}") },
                navigateToSettings = { navController.navigate(SettingsDestination.route) }
            )
        }
        composable(
            route = ChatDestination.routeWithArgs,
            arguments = listOf(
                navArgument(ChatDestination.chatIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            ChatScreen(
                navigateToChat = { navController.navigate("${ChatDestination.route}/${it}") },
                navigateUp = { navController.navigateUp() }
            )
        }
        composable(route = SettingsDestination.route) {
            SettingsScreen(
                //navigateBack = { navController.popBackStack() }
            )
        }
    }
}
