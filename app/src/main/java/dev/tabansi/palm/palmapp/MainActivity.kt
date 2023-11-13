package dev.tabansi.palm.palmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dev.tabansi.palm.palmapp.data.entity.Chat
import dev.tabansi.palm.palmapp.sample.SampleData
import dev.tabansi.palm.palmapp.ui.HomeScreen
import dev.tabansi.palm.palmapp.ui.theme.PalmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PalmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var id = 0
                    var time= 0
                    HomeScreen(
                        chatList = SampleData.sampleTitles.map { Chat(id++, it, true, (time++).toLong()) },
                        onChatClick = {  }
                    )
                }
            }
        }
    }
}
