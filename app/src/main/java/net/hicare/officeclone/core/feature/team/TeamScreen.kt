package net.hicare.officeclone.core.feature.team

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import net.hicare.officeclone.R
import net.hicare.officeclone.core.network.User

@Composable
fun TeamScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamViewModel = hiltViewModel()
) {
    val userList = viewModel.userList

    TeamScreen(
        modifier = modifier,
        userList = userList
    )
}

@Composable
private fun TeamScreen(
    modifier: Modifier = Modifier,
    userList: List<User>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.default_margin))
    ) {
        SearchBar()

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_margin)))

        UserFeed(
            user = User(
                id = "22",
                name = "My Account",
                number = "01011111111",
                message = "status",
                group = "Group2",
                isFavorite = false
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        LazyColumn {
            items(userList) { user ->
                UserFeed(user = user)
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
    user: User
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
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
                    max = 120.dp
                ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = user.message,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            )
        )
    }
}

@Preview
@Composable
private fun UserFeedPreview() {
    Surface {
        TeamScreen(
            userList = listOf(
                User(
                    id = "1",
                    name = "김김김",
                    number = "01012312311",
                    message = "status message",
                    group = "Group1",
                    isFavorite = true
                ),
                User(
                    id = "2",
                    name = "김김김2",
                    number = "01011111111",
                    message = "status",
                    group = "Group2",
                    isFavorite = false
                ),
                User(
                    id = "3",
                    name = "김김김",
                    number = "01012312311",
                    message = "status message",
                    group = "Group",
                    isFavorite = false
                ),
                User(
                    id = "4",
                    name = "test test",
                    number = "01012312311",
                    message = "test",
                    group = "Group",
                    isFavorite = false
                ),
                User(
                    id = "5",
                    name = "name1",
                    number = "01012312311",
                    message = "status message",
                    group = "Group",
                    isFavorite = true
                ),
            )
        )
    }
}