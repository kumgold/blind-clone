package net.example.officeclone.navigation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import net.example.officeclone.core.feature.chattingroom.nav.chattingRoom
import net.example.officeclone.core.feature.chattingroomlist.nav.chattingRoomListScreen
import net.example.officeclone.core.feature.settings.nav.settingsScreen
import net.example.officeclone.core.feature.team.nav.TeamRoute
import net.example.officeclone.core.feature.team.nav.teamScreen
import net.example.officeclone.core.feature.work.nav.workScreen
import net.example.officeclone.navigation.ui.OfficeAppState

@Composable
fun OfficeNavHost(
    modifier: Modifier = Modifier,
    appState: OfficeAppState
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
        chattingRoomListScreen()
        workScreen()
        settingsScreen()

        chattingRoom(
            onDismiss = navController::popBackStack
        )
    }
}