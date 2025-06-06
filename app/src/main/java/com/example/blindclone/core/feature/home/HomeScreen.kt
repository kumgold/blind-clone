package com.example.blindclone.core.feature.home

import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.blindclone.R
import com.example.blindclone.core.model.Post
import com.example.blindclone.ui.component.HomeTopAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToWriteScreen: () -> Unit,
    navigateToPostDetail: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        modifier = modifier,
        posts = uiState.posts,
        navigateToWriteScreen = navigateToWriteScreen,
        navigateToPostDetail = navigateToPostDetail
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    navigateToWriteScreen: () -> Unit,
    navigateToPostDetail: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HomeTopAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToWriteScreen() },
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValue ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValue)
                .padding(horizontal = dimensionResource(id = R.dimen.default_margin)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_margin))
        ) {
            items(posts) { post ->
                PostItem(
                    post = post,
                    navigateToPostDetail = navigateToPostDetail
                )
            }
        }
    }
}

@Composable
private fun PostItem(
    post: Post,
    navigateToPostDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clickable {
                navigateToPostDetail(post.id)
            }
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(
                    text = "Keyword",
                    style = TextStyle(color = MaterialTheme.colorScheme.primary),
                    fontSize = 11.sp
                )
                Text(
                    text = "Company name",
                    style = TextStyle(color = Color.LightGray),
                    fontSize = 10.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = post.title,
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
        Text(
            text = post.content,
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 13.sp
            ),
            maxLines = 2
        )
    }
}

@Preview
@Composable
private fun PostItemPreview() {
    val posts = listOf(
        Post(title = "테스트 1", content = "테스트 테스트"),
        Post(title = "테스트 2", content = "테스트 테스트")
    )

    Surface {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(posts) { post ->
                PostItem(post = post, navigateToPostDetail = {})
            }
        }
    }
}
