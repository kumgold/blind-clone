package com.example.blindclone.core.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.blindclone.R
import com.example.blindclone.ui.component.HomeTopAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToWriteScreen: () -> Unit
) {
    HomeScreenContent(
        modifier = modifier,
        navigateToWriteScreen = navigateToWriteScreen
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navigateToWriteScreen: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HomeTopAppBar()
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
        Column(
            modifier = Modifier.padding(paddingValue)
        ) {

        }
    }
}
