package net.example.blindclone.core.feature.chattingroom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import net.example.blindclone.R
import net.example.blindclone.core.model.Chat
import net.example.blindclone.ui.compose.BubbleShape
import net.example.blindclone.ui.compose.MessageDirection


@Composable
fun ChattingRoomScreen(
    modifier: Modifier = Modifier,
    viewModel: ChattingRoomViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val chatList = viewModel.chatList.collectAsStateWithLifecycle()

    ChattingRoomScreen(
        modifier = modifier,
        chatList = chatList.value,
        onDismiss = onDismiss,
        sendChat = viewModel::sendChat
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChattingRoomScreen(
    modifier: Modifier = Modifier,
    chatList: List<Chat>,
    onDismiss: () -> Unit,
    sendChat: (String) -> Unit
) {
    BasicAlertDialog(
        modifier = modifier.imePadding(),
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = dimensionResource(id = R.dimen.default_margin))
                .fillMaxSize()
        ) {
            val coroutineScope = rememberCoroutineScope()
            val scrollState = rememberLazyListState()

            LaunchedEffect(chatList.size) {
                if (chatList.isNotEmpty()) {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(chatList.lastIndex)
                    }
                }
            }

            TopAppBar(
                title = {
                    Text("user name")
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            onDismiss()
                        },
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            )
            LazyColumn(
                modifier = modifier
                    .weight(1f)
                    .padding(horizontal = dimensionResource(R.dimen.default_margin)),
                state = scrollState
            ) {
                items(
                    items = chatList,
                    key = { chat -> chat.id }
                ) { chat ->
                    if (chat.memberId == "1") {
                        MyChattingMessage(chat)
                    } else {
                        OtherChattingMessage(chat)
                    }
                }
            }
            ChattingInput(
                sendChat = { id ->
                    sendChat(id)
                }
            )
        }
    }
}

@Composable
private fun MyChattingMessage(chat: Chat) {
    Row(
        modifier = Modifier.height(IntrinsicSize.Max)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .background(color = Color.Green)
                .padding(dimensionResource(id = R.dimen.default_margin)),
            text = chat.message,
            fontSize = 22.sp,
            color = Color.Black
        )
        Box(
            modifier = Modifier
                .background(
                    color = Color.Green,
                    shape = BubbleShape(MessageDirection.RIGHT)
                )
                .fillMaxHeight()
        )
    }
}

@Composable
private fun OtherChattingMessage(chat: Chat) {
    Row(
        modifier = Modifier.height(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = BubbleShape(MessageDirection.LEFT)
                )
                .fillMaxHeight()
        )
        Text(
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(dimensionResource(id = R.dimen.default_margin)),
            text = chat.message,
            fontSize = 22.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun ChattingInput(
    modifier: Modifier = Modifier,
    sendChat: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.default_margin)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var message by remember { mutableStateOf("") }

        TextField(
            modifier = modifier
                .weight(1f)
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.default_margin))
                ),
            value = message,
            onValueChange = {
                message = it
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.default_margin)))
        FilledIconButton(
            modifier = modifier.size(56.dp),
            onClick = {
                sendChat(message)
                message = ""
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun ChatDetailScreenPreview() {
    Surface {
        ChattingRoomScreen(
            chatList = listOf(
                Chat("111", System.currentTimeMillis(), "message", "1"),
                Chat("111", System.currentTimeMillis(), "message", "2"),
                Chat("111", System.currentTimeMillis(), "message", "1"),
                Chat("111", System.currentTimeMillis(), "message", "2"),
            ),
            onDismiss = {},
            sendChat = {}
        )
    }
}