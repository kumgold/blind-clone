package net.example.officeclone.navigation.ui

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
import net.example.officeclone.core.data.NetworkMonitor
import net.example.officeclone.core.feature.chat.nav.navigateToChattingRoomList
import net.example.officeclone.core.feature.chattingroom.nav.navigateToChattingRoom
import net.example.officeclone.core.feature.settings.nav.navigateToSettings
import net.example.officeclone.core.feature.team.nav.navigateToTeam
import net.example.officeclone.core.feature.work.nav.navigateToWork
import net.example.officeclone.navigation.nav.TopLevelDestination
import net.example.officeclone.navigation.nav.TopLevelDestination.CHAT
import net.example.officeclone.navigation.nav.TopLevelDestination.SETTINGS
import net.example.officeclone.navigation.nav.TopLevelDestination.TEAM
import net.example.officeclone.navigation.nav.TopLevelDestination.WORK

@Composable
fun rememberOfficeAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): OfficeAppState {
    return remember(
        networkMonitor,
        coroutineScope,
        networkMonitor
    ) {
        OfficeAppState(
            networkMonitor = networkMonitor,
            coroutineScope = coroutineScope,
            navController = navController
        )
    }
}

@Stable
class OfficeAppState(
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
                TEAM -> navController.navigateToTeam(topLevelNavOptions)
                CHAT -> navController.navigateToChattingRoomList(topLevelNavOptions)
                WORK -> navController.navigateToWork(topLevelNavOptions)
                SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
            }
        }
    }

    fun navigateToChattingRoomDialog(chattingRoomId: String) {
        navController.navigateToChattingRoom(
            chattingRoomId = chattingRoomId
        )
    }
}