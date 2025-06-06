package com.example.blindclone.core.feature.postdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PostDetailScreen(
    postId: String,
    viewModel: PostDetailViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    LaunchedEffect(postId) {
        viewModel.fetchItemById(postId)
    }

    val item by viewModel.selectedItem.collectAsStateWithLifecycle()

    if (item == null) {
        CircularProgressIndicator()
    } else {
        Column {
            Text(text = item!!.title)
            Text(text = item!!.content)
        }
    }
}