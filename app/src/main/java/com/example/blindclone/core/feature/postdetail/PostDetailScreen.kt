package com.example.blindclone.core.feature.postdetail

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.blindclone.R
import com.example.blindclone.core.model.Post
import com.example.blindclone.ui.component.PostAppBar

@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    postId: String,
    viewModel: PostDetailViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    LaunchedEffect(postId) {
        viewModel.fetchItemById(postId)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            PostAppBar(
                popBackStack = popBackStack
            )
        },
    ) { paddingValue ->
        val item by viewModel.selectedItem.collectAsStateWithLifecycle()

        if (item == null) {
            CircularProgressIndicator(
                modifier = Modifier.wrapContentSize()
            )
        } else {
            item?.let { post ->
                PostDetail(
                    modifier = Modifier.padding(paddingValue),
                    post = post
                )
            }
        }
    }
}

@Composable
private fun PostDetail(
    modifier: Modifier = Modifier,
    post: Post
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.default_margin),
                horizontal = dimensionResource(id = R.dimen.default_margin_small)
            )
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
    ) {
        Text(
            text = post.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_margin_small)))
        Text(text = post.content)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.default_margin_small)),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "좋아요", fontSize = 12.sp, color = Color.LightGray)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.ChatBubbleOutline,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "댓글", fontSize = 12.sp, color = Color.LightGray)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.IosShare,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "공유하기", fontSize = 12.sp, color = Color.LightGray)
            }
        }
    }
}