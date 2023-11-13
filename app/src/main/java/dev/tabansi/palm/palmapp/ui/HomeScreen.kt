package dev.tabansi.palm.palmapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tabansi.palm.palmapp.R
import dev.tabansi.palm.palmapp.data.entity.Chat
import dev.tabansi.palm.palmapp.sample.SampleData

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    var id = 0
    var time= 0
    HomeScreen(
        chatList = SampleData.sampleTitles.map { Chat(id++, it, true, (time++).toLong()) },
        onChatClick = {  }
    )
}

@Composable
fun HomeScreen(
    chatList: List<Chat>,
    onChatClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(chatList) { chat ->
            ChatCard(title = chat.title, onChatClick = { /*TODO*/ })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatCard(
    title: String,
    onChatClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onChatClick,
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
                    .padding(
                        start = 16.dp,
                        top = 12.dp,
                        bottom = 12.dp
                    )
            ) {
                Text(
                    text = title,
                    modifier = Modifier,
//                        .align(Alignment.End)
//                        .padding(
//                            start = 16.dp,
//                            top = 16.dp,
//                            bottom = 16.dp,
//                            end = 0.dp
//                        )
//                        .weight(1f),
                    overflow = TextOverflow.Ellipsis,
                    minLines = 2,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Placeholder time",
                    maxLines = 1
                )
            }
            IconButton(onClick = onChatClick) {
                Icon(
                    painter = painterResource(id = R.drawable.options),
                    contentDescription = "Options",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

//@Preview
@Composable
fun ChatCardPreview() {
    ChatCard(
        title = "The Raven by Edgar Allen Po and snow white and the three little pigse",
        onChatClick = { }
    )
}