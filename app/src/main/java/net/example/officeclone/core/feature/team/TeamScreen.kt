package net.example.officeclone.core.feature.team

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.example.officeclone.R
import net.example.officeclone.core.feature.team.user.UserDialog
import net.example.officeclone.core.network.data.User

@Composable
fun TeamScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamViewModel = hiltViewModel(),
    navigateToChattingRoom: (String) -> Unit
) {
    val userList = viewModel.userList

    TeamScreen(
        modifier = modifier,
        userList = userList,
        navigateToChattingRoom = navigateToChattingRoom
    )
}

@Composable
private fun TeamScreen(
    modifier: Modifier = Modifier,
    userList: List<User>,
    navigateToChattingRoom: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.default_margin))
            .fillMaxSize()
    ) {
        SearchBar()

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_margin)))

        UserFeed(
            user = User(
                id = "1",
                name = "My Account",
                number = "01011111111",
                message = "message"
            ),
            navigateToChattingRoom = navigateToChattingRoom
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        LazyColumn {
            items(userList) { user ->
                key(user.id) {
                    UserFeed(
                        user = user,
                        navigateToChattingRoom = navigateToChattingRoom
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = {
            searchText = it
        },
        prefix = {
            Row {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.default_margin)))
            }
        },
        maxLines = 1
    )
}

@Composable
private fun UserFeed(
    modifier: Modifier = Modifier,
    user: User,
    navigateToChattingRoom: (String) -> Unit
) {
    var showUserDialog by remember { mutableStateOf(false) }

    if (showUserDialog) {
        UserDialog(
            user = user,
            navigateToChattingRoom = navigateToChattingRoom
        ) {
            showUserDialog = false
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                showUserDialog = true
            }
            .padding(dimensionResource(id = R.dimen.default_margin)),
        horizontalArrangement = Arrangement.Center
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

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .widthIn(
                    min = 0.dp,
                    max = 120.dp
                ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = user.name,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            )
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.default_margin)))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .widthIn(
                    min = 0.dp,
                    max = 100.dp
                ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = user.message,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun UserFeedPreview() {
    Surface {
        TeamScreen(
            modifier = Modifier,
            userList = listOf(
                User(
                    id = "1",
                    name = "김김김",
                    number = "01011111111",
                    message = "status message"
                ),
                User(
                    id = "2",
                    name = "김김김2",
                    number = "01011111111",
                    message = "status message"
                ),
                User(
                    id = "3",
                    name = "별명",
                    number = "01012341211",
                    message = "status message"
                ),
                User(
                    id = "4",
                    name = "dldldl",
                    number = "01012311111",
                    message = "status message"
                ),
                User(
                    id = "5",
                    name = "test test",
                    number = "01011111111",
                    message = "status message"
                ),
            ),
            navigateToChattingRoom = {}
        )
    }
}