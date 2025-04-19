package com.example.blindclone.core.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.blindclone.R
import com.example.blindclone.ui.component.HomeTopAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    HomeScreenContent(modifier = modifier)
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.default_margin))
            .fillMaxSize(),
        topBar = {
            HomeTopAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }
            ) {

            }
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier.padding(paddingValue)
        ) {

        }
    }
}
