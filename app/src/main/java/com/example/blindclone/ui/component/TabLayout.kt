package com.example.blindclone.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.blindclone.R
import kotlinx.coroutines.launch

@Composable
fun TabLayout(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    pagerState: PagerState,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.default_margin)),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabs ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabs[pagerState.currentPage]),
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            tabs.forEachIndexed { index, tab ->
                val selected = (pagerState.currentPage == index)

                Tab(
                    selected = selected,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.default_margin)),
                        text = tab,
                        fontWeight = FontWeight.Bold,
                        color = if (selected) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            Color.LightGray
                        },
                    )
                }
            }
        }

        content()
    }
}