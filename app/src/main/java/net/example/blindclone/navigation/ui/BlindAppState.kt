package net.example.blindclone.navigation.ui

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.example.blindclone.core.data.NetworkMonitor
import net.example.blindclone.core.feature.channel.nav.navigateToChannel
import net.example.blindclone.core.feature.corporation.nav.navigateToCorporation
import net.example.blindclone.core.feature.employment.nav.navigateToEmployment
import net.example.blindclone.core.feature.home.nav.navigateToHome
import net.example.blindclone.core.feature.notification.nav.navigateToNotification
import net.example.blindclone.navigation.nav.TopLevelDestination
import net.example.blindclone.navigation.nav.TopLevelDestination.CHANNEL
import net.example.blindclone.navigation.nav.TopLevelDestination.CORPORATION
import net.example.blindclone.navigation.nav.TopLevelDestination.EMPLOYMENT
import net.example.blindclone.navigation.nav.TopLevelDestination.HOME
import net.example.blindclone.navigation.nav.TopLevelDestination.NOTIFICATION

@Composable
fun rememberOfficeAppState(
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

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation : ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                HOME -> navController.navigateToHome(topLevelNavOptions)
                CORPORATION -> navController.navigateToCorporation(topLevelNavOptions)
                CHANNEL -> navController.navigateToChannel(topLevelNavOptions)
                EMPLOYMENT -> navController.navigateToEmployment(topLevelNavOptions)
                NOTIFICATION -> navController.navigateToNotification(topLevelNavOptions)
            }
        }
    }
}