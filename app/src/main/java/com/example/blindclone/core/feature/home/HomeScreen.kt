package com.example.blindclone.core.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.blindclone.R
import com.example.blindclone.core.model.Post
import com.example.blindclone.ui.component.MainTopAppBar
import com.example.blindclone.ui.component.PostList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    navigateToWriteScreen: () -> Unit,
    navigateToPostDetail: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        modifier = modifier,
        posts = uiState.posts,
        navController = navController,
        navigateToWriteScreen = navigateToWriteScreen,
        navigateToPostDetail = navigateToPostDetail
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    navController: NavController,
    navigateToWriteScreen: () -> Unit,
    navigateToPostDetail: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.blind),
                navController = navController
            )
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

        PostList(
            modifier = Modifier.padding(paddingValue),
            posts = posts,
            navigateToPostDetail = navigateToPostDetail
        )
    }
}
