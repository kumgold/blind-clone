package com.example.blindclone.feature.channel

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.blindclone.R
import com.example.blindclone.core.model.Channel
import com.example.blindclone.core.model.Post
import com.example.blindclone.feature.home.HomeViewModel
import com.example.blindclone.navigation.RootRoute
import com.example.blindclone.ui.component.AdBox
import com.example.blindclone.ui.component.MainTopAppBar
import com.example.blindclone.ui.component.TabLayout

@Composable
fun ChannelScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.channel),
                navController = navController
            )
        },
    ) { paddingValue ->
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        val tabs = listOf("탐색", "내 채널")
        val pagerState = rememberPagerState {
            tabs.size
        }

        TabLayout(
            modifier = Modifier.padding(paddingValue),
            tabs = tabs,
            pagerState = pagerState
        ) {
            HorizontalPager(
                state = pagerState
            ) { index ->
                when (tabs[index]) {
                    "탐색" -> {
                        SearchChannel(
                            posts = uiState.value.posts,
                            navigateToPostDetail = { id ->
                                navController.navigate("${RootRoute.PostDetail}/$id")
                            }
                        )
                    }
                    "내 채널" -> {
                        MyChannel()
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchChannel(
    posts: List<Post>,
    navigateToPostDetail: (String) -> Unit
) {
    Column {
        RecommandChannel()
        AdBox()
        FavoritePost(
            posts = posts,
            navigateToPostDetail = navigateToPostDetail
        )
    }
}

@Composable
private fun RecommandChannel() {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.default_margin)),
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.default_margin)
            ),
            text = "당신을 위한 추천",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.default_margin)
            )
        ) {
            items(channels) { channel ->
                ChannelCard(
                    channel = channel
                )
            }
        }
    }
}

@Composable
private fun ChannelCard(
    channel: Channel
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clickable {

            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_margin_small)))
        Text(
            text = channel.title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Text(
            text = channel.description,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_margin_small)))
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(
                    horizontal = dimensionResource(id = R.dimen.default_margin)
                )
                .clickable { },
            text = "팔로우",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun FavoritePost(
    posts: List<Post>,
    navigateToPostDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.default_margin)),
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.default_margin)
            ),
            text = "실시간 인기글",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.default_margin_small)
            )
        ) {
            items(posts) { post ->
                FavoritePostItem(
                    post = post,
                    navigateToPostDetail = { id ->
                        navigateToPostDetail(id)
                    }
                )
            }
        }
    }
}

@Composable
private fun FavoritePostItem(
    post: Post,
    navigateToPostDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .size(320.dp, 180.dp)
            .clickable {
                navigateToPostDetail(post.id)
            },
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.default_margin_small)),
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
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.default_margin_small)))
                Text(
                    text = post.keyword,
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_margin_small)))
            Text(
                text = post.title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = post.content,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp
                ),
                maxLines = 2
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.default_margin_small)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.primary
                        )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Company name", fontSize = 12.sp, color = Color.LightGray)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.RemoveRedEye,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "조회수", fontSize = 12.sp, color = Color.LightGray)
            }
        }
    }
}

@Composable
private fun MyChannel() {

}

@Preview
@Composable
private fun ChannelCardPreview() {
    Surface {
        ChannelCard(
            channel = Channel(
                title = "xptmxm",
                description = "test",
                thumbnail = ""
            )
        )
    }
}