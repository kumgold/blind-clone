package net.hicare.officeclone.core.feature.chat.detail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.hicare.officeclone.R
import net.hicare.officeclone.ui.compose.BubbleShape

@Serializable data object ChatDetailRoute

fun NavController.navigateToChatDetail() = navigate(route = ChatDetailRoute)

fun NavGraphBuilder.chatDetailScreen() {
    composable<ChatDetailRoute> {
        ChatDetailScreen()
    }
}

@Composable
fun ChatDetailScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.default_margin))
    ) {
        ChattingMessage()
        ChattingMessage()
        Spacer(modifier = Modifier.weight(1f))
        ChattingInput()
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
            .padding(vertical = dimensionResource(id = R.dimen.default_margin)),
        value = searchText,
        onValueChange = {
            searchText = it
        },
        maxLines = 1
    )
}

@Composable
private fun ChattingMessage() {
    Row(
        modifier = Modifier.height(IntrinsicSize.Max)
    ) {
        Text(
            modifier = Modifier
                .background(color = Color.Green)
                .padding(horizontal = 10.dp),
            text = "test",
            fontSize = 22.sp
        )
        Box(
            modifier = Modifier
                .background(
                    color = Color.Green,
                    shape = BubbleShape()
                )
                .width(8.dp)
                .fillMaxHeight()
        )
    }
}

@Preview
@Composable
private fun ChatDetailScreenPreview() {
    Surface {
        ChatDetailScreen()
    }
}