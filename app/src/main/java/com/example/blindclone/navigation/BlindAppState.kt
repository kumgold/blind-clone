package com.example.blindclone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.blindclone.core.data.NetworkMonitor
import com.example.blindclone.feature.channel.nav.navigateToChannel
import com.example.blindclone.feature.corporation.nav.navigateToCorporation
import com.example.blindclone.feature.employment.nav.navigateToEmployment
import com.example.blindclone.feature.home.nav.navigateToHome
import com.example.blindclone.feature.main.MainDestination
import com.example.blindclone.feature.notification.nav.navigateToNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberBlindAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): BlindAppState {
    return remember(
        networkMonitor,
        coroutineScope,
        networkMonitor
    ) {
        BlindAppState(
            networkMonitor = networkMonitor,
            coroutineScope = coroutineScope,
            navController = navController
        )
    }
}

@Stable
class BlindAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val mainDestinations: List<MainDestination> = MainDestination.entries

    fun navigateToMainDestination(mainDestination: MainDestination) {
        trace("Navigation : ${mainDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (mainDestination) {
                MainDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                MainDestination.CORPORATION -> navController.navigateToCorporation(topLevelNavOptions)
                MainDestination.CHANNEL -> navController.navigateToChannel(topLevelNavOptions)
                MainDestination.EMPLOYMENT -> navController.navigateToEmployment(topLevelNavOptions)
                MainDestination.NOTIFICATION -> navController.navigateToNotification(topLevelNavOptions)
            }
        }
    }
}