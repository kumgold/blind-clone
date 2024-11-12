package net.hicare.officeclone.navigation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.hicare.officeclone.core.feature.chat.detail.ChatDetailScreen
import net.hicare.officeclone.core.feature.chat.detail.chatDetailScreen
import net.hicare.officeclone.core.feature.chat.detail.navigateToChatDetail
import net.hicare.officeclone.core.feature.chat.nav.chatScreen
import net.hicare.officeclone.core.feature.settings.nav.settingsScreen
import net.hicare.officeclone.core.feature.team.TeamScreen
import net.hicare.officeclone.core.feature.team.nav.TeamRoute
import net.hicare.officeclone.core.feature.team.nav.teamScreen
import net.hicare.officeclone.core.feature.work.nav.workScreen
import net.hicare.officeclone.navigation.ui.OfficeAppState

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
        teamScreen()
        chatScreen(
            onChatClick = navController::navigateToChatDetail
        )
        workScreen()
        settingsScreen()

        chatDetailScreen()
    }
}