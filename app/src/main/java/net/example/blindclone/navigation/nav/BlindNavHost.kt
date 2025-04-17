package net.example.blindclone.navigation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import net.example.blindclone.navigation.nav.TopLevelDestination
import net.example.blindclone.core.feature.chattingroom.nav.chattingRoom
import net.example.blindclone.core.feature.chattingroomlist.nav.chattingRoomListScreen
import net.example.blindclone.core.feature.settings.nav.settingsScreen
import net.example.blindclone.core.feature.team.nav.TeamRoute
import net.example.blindclone.core.feature.team.nav.teamScreen
import net.example.blindclone.core.feature.work.nav.workScreen
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
        startDestination = TeamRoute
    ) {
        teamScreen(
            navigateToChattingRoom = { id ->
                appState.navigateToTopLevelDestination(TopLevelDestination.CHAT)
                appState.navigateToChattingRoomDialog(id)
            }
        )
        chattingRoomListScreen(
            navigateToChattingRoom = { id ->
                appState.navigateToChattingRoomDialog(id)
            }
        )
        workScreen()
        settingsScreen()
        chattingRoom(
            onDismiss = navController::popBackStack
        )
    }
}