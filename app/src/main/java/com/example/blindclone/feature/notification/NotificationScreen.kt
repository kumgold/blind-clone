package com.example.blindclone.feature.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.blindclone.R
import com.example.blindclone.ui.component.MainTopAppBar
import com.example.blindclone.ui.component.TabLayout

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.notification),
                navController = navController
            )
        },
    ) { paddingValue ->
        val tabs = listOf("새 소식", "키워드")
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
                Text(
                    text = tabs[index],
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}