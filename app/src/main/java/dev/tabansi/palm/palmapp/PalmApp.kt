package dev.tabansi.palm.palmapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.tabansi.palm.palmapp.ui.ChatScreen
import dev.tabansi.palm.palmapp.ui.HomeScreen
import dev.tabansi.palm.palmapp.ui.SettingsScreen
import dev.tabansi.palm.palmapp.ui.ChatViewModel

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object Chat : Screens("chat")
    data object Settings : Screens("settings")

    companion object {
        fun fromRoute(route: String?): Screens =
            when (route) {
                Home.route -> Home
                Chat.route -> Chat
                Settings.route -> Settings
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}

@Composable
fun PalmApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.fromRoute(backStackEntry?.destination?.route)

    Box {
        Scaffold(
            topBar = {
                PalmTopAppBar(currentScreen, { navController.navigateUp() })
            }
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route
            ) {
                composable(route = Screens.Home.route) {
                    HomeScreen(
                        chatList = listOf(),
                        onChatClick = {
                            navController.navigate(Screens.Chat.route)
                        },
                        modifier = Modifier.padding(contentPadding)
                    )
                }
                composable(route = "${Screens.Chat.route}/{chatId}") { backStackEntry ->
                    val chatId = backStackEntry.arguments?.getInt("chatId")
                    val chatViewModel = ChatViewModel(chatId)
                    ChatScreen(
                        navigateUp = { navController.navigateUp() },
                        chatViewModel = chatViewModel,
                        modifier = Modifier.padding(contentPadding)
                    )
                }
                composable(route = Screens.Settings.route) {
                    SettingsScreen(
                        modifier = Modifier.padding(contentPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PalmTopAppBar(
    currentScreen: Screens,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (currentScreen) {
        Screens.Home -> {
            HomeTopAppBar(modifier)
        }
        Screens.Chat -> {
            ChatTopAppBar(
                title = "",
                titleSet = false,
                navigateUp = navigateUp
            )
        }
        Screens.Settings -> {
            SettingsTopAppBar(
                navigateUp = navigateUp,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier
) { 
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.palm_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp)
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopAppBar(
    title: String,
    titleSet: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = if (titleSet) title else "New Chat",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier.shadow(8.dp), // TODO lift shadow up to be passed to all top bars
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewChatTopAppBar() {
    ChatTopAppBar(
        title = "The  Raven by Edgar Allen Poe and the twelve dwarfs of snow white's story",
        titleSet = true,
        navigateUp = { /*TODO*/ }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopAppBar(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.settings),
                style = MaterialTheme.typography.displaySmall
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        }
    )
}

