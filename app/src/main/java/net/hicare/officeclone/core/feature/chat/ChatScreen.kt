package net.hicare.officeclone.core.feature.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.hicare.officeclone.R

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    onChatClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.default_margin))
    ) {
        Text(
            text = "Chattings",
            style = MaterialTheme.typography.titleLarge
        )
        ChatFeed(
            message = "test testtest testtest testtest testtest testtest testtest test ",
            onChatClick = onChatClick
        )

    }
}

@Composable
private fun ChatFeed(
    modifier: Modifier = Modifier,
    message: String = "",
    onChatClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onChatClick() }
            .padding(dimensionResource(id = R.dimen.default_margin))
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Gray)
                .padding(dimensionResource(id = R.dimen.default_margin)),
            imageVector = Icons.Default.Person,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.default_margin)))
        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .widthIn(
                    min = 0.dp,
                    max = 300.dp
                ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = message,
            style = MaterialTheme.typography.bodyLarge.copy(Color.Gray)
        )
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    Surface {
        ChatScreen(
            onChatClick = {}
        )
    }
}