package com.example.blindclone.core.feature.corporation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.blindclone.R
import com.example.blindclone.core.feature.home.HomeViewModel
import com.example.blindclone.core.model.Post
import com.example.blindclone.navigation.RootRoute
import com.example.blindclone.ui.component.AdBox
import com.example.blindclone.ui.component.MainTopAppBar
import com.example.blindclone.ui.component.PostList
import com.example.blindclone.ui.component.TabLayout

@Composable
fun CorporationScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.corporation),
                navController = navController
            )
        },
    ) { paddingValue ->
        val tabs = listOf("내 회사", "팔로잉 회사")
        val pagerState = rememberPagerState {
            tabs.size
        }

        TabLayout(
            modifier = Modifier.padding(paddingValue),
            tabs = tabs,
            pagerState = pagerState
        ) {
            HorizontalPager(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.default_margin_small)),
                state = pagerState
            ) { index ->
                when (tabs[index]) {
                    "내 회사" -> {
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                        MyCompany(
                            posts = uiState.posts,
                            navigateToPostDetail = { id ->
                                navController.navigate("${RootRoute.PostDetail}/${id}")
                            }
                        )
                    }
                    "팔로잉 회사" -> {
                        FollowingCompany()
                    }
                }
            }
        }
    }
}

@Composable
private fun MyCompany(
    posts: List<Post>,
    navigateToPostDetail: (String) -> Unit
) {
    Column {
        AdBox()
        PostList(
            posts = posts,
            navigateToPostDetail = { id ->
                navigateToPostDetail(id)
            }
        )
    }
}

@Composable
private fun FollowingCompany() {

}
