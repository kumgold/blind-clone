package com.example.blindclone.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blindclone.R
import com.example.blindclone.core.model.Post

@Composable
fun PostList(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    navigateToPostDetail: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.default_margin)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_margin))
    ) {
        items(posts) { post ->
            PostItem(
                post = post,
                navigateToPostDetail = navigateToPostDetail
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = (0.5).dp,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
private fun PostItem(
    post: Post,
    navigateToPostDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigateToPostDetail(post.id)
            }
            .padding(
                vertical = dimensionResource(id = R.dimen.default_margin_small)
            )
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
            Column {
                Text(
                    text = post.keyword,
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                    fontSize = 12.sp
                )
                Text(
                    text = "Company name",
                    style = TextStyle(color = Color.LightGray),
                    fontSize = 10.sp
                )
            }
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
                    imageVector = Icons.Default.RemoveRedEye,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "조회수", fontSize = 12.sp, color = Color.LightGray)
            }
        }
    }
}