package dev.tabansi.palm.palmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dev.tabansi.palm.palmapp.data.AppData
import dev.tabansi.palm.palmapp.ui.screens.ChatScreen
import dev.tabansi.palm.palmapp.ui.screens.MessageList
import dev.tabansi.palm.palmapp.ui.screens.MessageTextField
import dev.tabansi.palm.palmapp.ui.theme.PALMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PALMTheme {
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