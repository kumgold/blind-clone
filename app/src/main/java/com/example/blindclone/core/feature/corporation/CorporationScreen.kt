package com.example.blindclone.core.feature.corporation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.blindclone.R
import com.example.blindclone.ui.component.MainTopAppBar
import com.example.blindclone.ui.component.TabLayout

@Composable
fun CorporationScreen(
    modifier: Modifier = Modifier,
    viewModel: CorporationViewModel = hiltViewModel(),
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

        TabLayout(
            modifier = Modifier.padding(paddingValue),
            tabs = tabs
        )
    }
}
