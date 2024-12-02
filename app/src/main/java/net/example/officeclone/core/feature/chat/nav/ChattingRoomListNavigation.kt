package net.example.officeclone.core.feature.chat.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.example.officeclone.core.feature.chat.ChattingRoomListScreen

@Serializable data object ChattingRoomListRoute

fun NavController.navigateToChattingRoomList(
    navOptions: NavOptions
) = navigate(route = ChattingRoomListRoute, navOptions)

fun NavGraphBuilder.chattingRoomListScreen() {
    composable<ChattingRoomListRoute> {
        ChattingRoomListScreen()
    }
}