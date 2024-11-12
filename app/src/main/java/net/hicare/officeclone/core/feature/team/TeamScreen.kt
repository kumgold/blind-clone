package net.hicare.officeclone.core.feature.team

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.hicare.officeclone.R

@Composable
fun TeamScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.default_margin))
    ) {
        SearchBar()

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_margin)))

        UserFeed(userName = "User A")

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        LazyColumn {
            items(10) {
                UserFeed(userName = "User $it")
            }
        }
    }
}

@Composable
private fun SearchBar(

) {
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
    userName: String = ""
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
            text = userName,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
private fun UserFeedPreview() {
    Surface {
        TeamScreen()
    }
}