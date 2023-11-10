package dev.tabansi.palm.palmapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tabansi.palm.palmapp.R
import dev.tabansi.palm.palmapp.data.AppData
import dev.tabansi.palm.palmapp.data.Message
import dev.tabansi.palm.palmapp.ui.theme.PALMTheme
import dev.tabansi.palm.palmapp.ui.viewmodels.ChatViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    navigateUp: () -> Unit,
    viewModel: ChatViewModel = viewModel()
) {
    val chatUiState = viewModel.chatUiState.collectAsState().value
    val textFieldHeight = remember { mutableIntStateOf(0) }
    Box {
        Scaffold(
            topBar = {
                ChatTopAppBar(
                    title = chatUiState.title,
                    titleSet = chatUiState.titleSet,
                    navigateUp = navigateUp
                )
            }
        ) {
            MessageList(
                contentPadding = it,
                textFieldHeight = textFieldHeight,
                messages = AppData.sampleChat.messages,
                modifier = Modifier //.weight(1f)
            )
        }
        MessageTextField(
            inputBlocked = true,//chatUiState.inputBlocked,
            onSendButtonClick = {  },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
                .onGloballyPositioned { textFieldHeight.intValue = it.size.height }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopAppBar(
    title: String,
    titleSet: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            if (titleSet)
                Text(title)
            else
                Text("New Chat")
        },
        modifier = modifier.shadow(8.dp),
        navigationIcon = {
            IconButton(
                onClick = navigateUp,
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }

    )
}

@Composable
fun MessageList(
    contentPadding: PaddingValues,
    textFieldHeight: MutableState<Int>,
    messages: List<Message>,
    modifier: Modifier = Modifier
) {

    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = modifier,
        contentPadding = PaddingValues(
            start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
            top = contentPadding.calculateTopPadding(),
            end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
            bottom = with(LocalDensity.current) { textFieldHeight.value.toDp() }
        )
    ) {
        items(messages) {
            MessageCard(
                id = it.id,
                text = it.text,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        lazyListState.animateScrollToItem(messages.size - 1)
        coroutineScope.launch {
            delay(200)
            lazyListState.animateScrollBy(10000f)
        }
    }
}

// TODO background neutral for user message, tertiary for ai
@Composable
fun MessageCard(
    id: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    if (id % 2 == 0) {
        Card(
            modifier = modifier.padding(8.dp),
            colors = CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(12.dp),
                textAlign = TextAlign.Right
            )
        }
    } else {
        Card(
            modifier = modifier.padding(8.dp),
            colors = CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageTextField(
    inputBlocked: Boolean,
    onSendButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var userInput by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = userInput,
        onValueChange = { userInput = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 0.dp)
            .imePadding(),
        shape = RoundedCornerShape(16.dp),
        maxLines = 8,
        trailingIcon = {
            if (!inputBlocked)
                SendInputIcon(onSendButtonClick = onSendButtonClick)
            else
                StopInputButton()
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SendInputIcon(
    onSendButtonClick: () -> Unit
) {
    IconButton(
        onClick = onSendButtonClick
    ) {
        Icon(
            painter = painterResource(R.drawable.send_input_icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun StopInputButton() {
    var showError by rememberSaveable {
        mutableStateOf(false)
    }
    IconButton(
        onClick = { showError = true }
    ) {
        Icon(
            painter = painterResource(R.drawable.block_input_icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(2.dp)
        )
    }

    if (showError) {
        DisplayToastError()
        showError = false
    }

}

@Composable
fun DisplayToastError() {
    val context = LocalContext.current
    val message = "Cannot submit new chat prompt while response is generating"
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun PreviewChatUi() {
    PALMTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            ChatScreen(navigateUp = { /*TODO*/ })
        }
    }
}