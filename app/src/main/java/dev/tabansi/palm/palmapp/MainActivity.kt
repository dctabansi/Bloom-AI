package dev.tabansi.palm.palmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import dev.tabansi.palm.palmapp.data.AppData
import dev.tabansi.palm.palmapp.ui.screens.ChatScreen
import dev.tabansi.palm.palmapp.ui.theme.PALMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        var useDarkTheme = false
        setContent {

            // val systemUiController = rememberSystemUiController()

            if (AppData.Settings.systemDefaultTheme)
                useDarkTheme = isSystemInDarkTheme()
            else if (AppData.Settings.darkTheme != null) {
                useDarkTheme = AppData.Settings.darkTheme!!
            } else {
                AppData.Settings.darkTheme = isSystemInDarkTheme()
                useDarkTheme = AppData.Settings.darkTheme!!
            }

            useDarkTheme = if (AppData.Settings.systemDefaultTheme)
                isSystemInDarkTheme()
            else
                AppData.Settings?.darkTheme ?: isSystemInDarkTheme()

            PALMTheme(darkTheme = useDarkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatScreen(navigateUp = { /*TODO*/ })
                }
            }
        }
    }
}