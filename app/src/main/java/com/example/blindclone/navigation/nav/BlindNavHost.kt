package com.example.blindclone.navigation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.blindclone.core.feature.channel.nav.channelScreen
import com.example.blindclone.core.feature.corporation.nav.corporationScreen
import com.example.blindclone.core.feature.employment.nav.employmentScreen
import com.example.blindclone.core.feature.home.nav.HomeRoute
import com.example.blindclone.core.feature.home.nav.homeScreen
import com.example.blindclone.core.feature.notification.nav.notificationScreen
import com.example.blindclone.navigation.ui.BlindAppState

@Composable
fun BlindNavHost(
    modifier: Modifier = Modifier,
    appState: BlindAppState
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute
    ) {
        homeScreen()
        corporationScreen()
        channelScreen()
        employmentScreen()
        notificationScreen()
    }
}