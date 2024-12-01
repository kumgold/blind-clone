package net.example.officeclone.core.feature.chattingroom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import net.example.officeclone.R
import net.example.officeclone.core.model.Chat
import net.example.officeclone.ui.compose.BubbleShape
import net.example.officeclone.ui.compose.MessageDirection


@Composable
fun ChattingRoomScreen(
    modifier: Modifier = Modifier,
    viewModel: ChattingRoomViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {


    ChattingRoomScreen(
        modifier = modifier,
        chatList = listOf(),
        onDismiss = onDismiss
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChattingRoomScreen(
    modifier: Modifier = Modifier,
    chatList: List<Chat>,
    onDismiss: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = dimensionResource(id = R.dimen.default_margin))
                .fillMaxSize()
        ) {
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
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f)
            ) {
                MyChattingMessage()
                OtherChattingMessage()
                MyChattingMessage()
                LazyColumn {
                    items(chatList) { chat ->
                        if (chat.id == "1") {
                            MyChattingMessage()
                        } else {
                            OtherChattingMessage()
                        }
                    }
                }
            }
            ChattingInput()
        }
    }
}

@Composable
private fun MyChattingMessage() {
    Row(
        modifier = Modifier.height(IntrinsicSize.Max)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .background(color = Color.Green)
                .padding(dimensionResource(id = R.dimen.default_margin)),
            text = "test test test",
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
private fun OtherChattingMessage() {
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
            text = "test",
            fontSize = 22.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun ChattingInput(
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.default_margin))
            .padding(bottom = 20.dp),
        value = searchText,
        onValueChange = {
            searchText = it
        },
        maxLines = 1
    )
}

@Preview
@Composable
private fun ChatDetailScreenPreview() {
    Surface {
        ChattingRoomScreen(
            chatList = listOf(),
            onDismiss = {}
        )
    }
}