package dev.tabansi.palm.palmapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tabansi.palm.palmapp.R
import dev.tabansi.palm.palmapp.data.entity.ChatMessage
import dev.tabansi.palm.palmapp.ui.theme.PalmTheme

@Composable
fun ChatScreen(
    navigateUp: () -> Unit,
//    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel = viewModel()
) {
    val chatUiState = chatViewModel.chatUiState.collectAsState().value
    val textFieldHeight = rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier
    ) {
        MessageList(
//            contentPadding = contentPadding,
            textFieldHeight = textFieldHeight,
            messages = chatUiState.messages,
            modifier = Modifier.weight(1f)
        )
        MessageTextField(
            inputBlocked = chatUiState.inputBlocked,
            onClick = {  },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .onGloballyPositioned { textFieldHeight.intValue = it.size.height }
        )
    }
}

// TODO align palm chat horizontal end
@Composable
fun MessageList(
    //contentPadding: PaddingValues,
    textFieldHeight: MutableState<Int>,
    messages: List<ChatMessage>,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier,
        state = lazyListState,
//        contentPadding = PaddingValues(
//            start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
//            top = contentPadding.calculateTopPadding(),
//            end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
//            bottom = with(LocalDensity.current) { textFieldHeight.value.toDp() }
//        )
    ) {
        items(messages) {
            MessageCard(
                user = it.user,
                text = it.text,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Spacer(
                modifier = Modifier.height(
                    with(LocalDensity.current) { textFieldHeight.value.toDp() }
                )
            )
        }
    }
    LaunchedEffect(messages) {
        lazyListState.animateScrollToItem(messages.size - 1)
//        coroutineScope.launch {
//            delay(200)
//            lazyListState.animateScrollBy(10000f)
//        }
    }
}

// TODO: background neutral (or maybe gray) for user message, tertiary or accent for palm message
@Composable
fun MessageCard(
    user: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor =
            if (user == "user") MaterialTheme.colorScheme.onSecondary
            else MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun MessageTextField(
    inputBlocked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userInput by rememberSaveable { mutableStateOf("") }

    TextField(
        value = userInput,
        onValueChange = { userInput = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 0.dp)
            .imePadding(),
        shape = RoundedCornerShape(16.dp),
        maxLines = 6,
        trailingIcon = { if (!inputBlocked) SendInputButton(onClick) else StopInputButton() },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SendInputButton(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(R.drawable.send_input_icon),
            contentDescription = "Send input",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun StopInputButton() {
    var showToast by rememberSaveable { mutableStateOf(false) }

    IconButton(
        onClick = { showToast = true }
    ) {
        Icon(
            painter = painterResource(R.drawable.block_input_icon),
            contentDescription = "Input is not currently available",
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(2.dp)
        )
    }

    if (showToast) {
        ToastError()
        showToast = false
    }
}

@Composable
fun ToastError() {
    val context = LocalContext.current
    val message = "Cannot submit new chat prompt while response is generating"
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun PreviewChatUi() {
    PalmTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            ChatScreen(
                navigateUp = { /*TODO*/ },
//                contentPadding = PaddingValues()
            )
        }
    }
}