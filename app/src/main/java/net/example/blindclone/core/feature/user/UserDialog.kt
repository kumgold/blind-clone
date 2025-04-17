package net.example.blindclone.core.feature.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import net.example.blindclone.R
import net.example.blindclone.core.model.Member

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDialog(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    member: Member,
    navigateToChattingRoom: (String) -> Unit,
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
                title = {},
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            onDismiss()
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            )
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .padding(dimensionResource(R.dimen.default_margin))
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally),
                imageVector = Icons.Default.Person,
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Text(text = member.name)
            Text(text = member.number)

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.default_margin)))
                    .background(Color.LightGray)
                    .clickable {
                        val chattingRoomId = "1-${member.id}"

                        viewModel.createChattingRoom(
                            id = chattingRoomId,
                            name = member.name,
                            memberIdList = listOf("1", member.id)
                        )
                        navigateToChattingRoom(chattingRoomId)
                    }
                    .padding(dimensionResource(R.dimen.default_margin)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.ChatBubble,
                    contentDescription = null
                )
                Text(text = "1:1 채팅")
            }
        }
    }
}

@Preview
@Composable
private fun UserDialogPreview() {
    Surface {
        UserDialog(
            member = Member(
                id = "1",
                name = "김김김",
                number = "01011111111",
                statusMessage = "status message"
            ),
            navigateToChattingRoom = {},
            onDismiss = {}
        )
    }
}