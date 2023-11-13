package dev.tabansi.palm.palmapp.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.ai.generativelanguage.v1beta3.Message

@Composable
fun SampleChatUi(
    sampleChatViewModel: SampleChatViewModel = viewModel()
) {
    val (inputText, setInputText) = remember { mutableStateOf("") }
    val messages: List<Message> by sampleChatViewModel.messages.collectAsState()
    Column(
        modifier = Modifier.padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText,
            onValueChange = setInputText,
            label = { Text("Input:") }
        )
        Button(
            onClick = {
                sampleChatViewModel.sendMessage(inputText)
                setInputText("")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Send Message")
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            reverseLayout = true
        ) {
            items(messages) { message ->
                Card(
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = message.author,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = message.content,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}