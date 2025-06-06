package com.example.blindclone.core.feature.channel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.blindclone.R
import com.example.blindclone.core.model.Channel
import com.example.blindclone.ui.component.MainTopAppBar
import com.example.blindclone.ui.component.TabLayout

@Composable
fun ChannelScreen(
    modifier: Modifier = Modifier,
    navController: NavController
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
                when (index) {
                    0 -> {
                        SearchChannel()
                    }
                    1 -> {

                    }
                }
            }
        }
    }
}

@Composable
private fun SearchChannel() {
    Column {
        RecommandChannel()
        Text(
            modifier = Modifier.padding(
                vertical = dimensionResource(id = R.dimen.default_margin),
                horizontal = dimensionResource(id = R.dimen.default_margin),
            ),
            text = "실시간 인기글",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun RecommandChannel() {
    Text(
        modifier = Modifier.padding(
            vertical = dimensionResource(id = R.dimen.default_margin),
            horizontal = dimensionResource(id = R.dimen.default_margin),
        ),
        text = "당신을 위한 추천",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    LazyRow(
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = R.dimen.default_margin_small)
        )
    ) {
        items(channels) { channel ->
            ChannelCard(
                channel = channel
            )
        }
    }
}

@Composable
private fun ChannelCard(
    channel: Channel
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.default_margin_small))
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
                .clickable {  },
            text = "팔로우",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
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