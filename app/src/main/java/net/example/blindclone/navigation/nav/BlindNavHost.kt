package net.example.blindclone.navigation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import net.example.blindclone.core.feature.channel.nav.channelScreen
import net.example.blindclone.core.feature.corporation.nav.corporationScreen
import net.example.blindclone.core.feature.employment.nav.employmentScreen
import net.example.blindclone.core.feature.home.nav.HomeRoute
import net.example.blindclone.core.feature.home.nav.homeScreen
import net.example.blindclone.core.feature.notification.nav.notificationScreen
import net.example.blindclone.navigation.ui.BlindAppState

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