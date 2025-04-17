package net.example.blindclone.core.feature.chattingroomlist.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.example.blindclone.core.feature.chattingroomlist.ChattingRoomListScreen

@Serializable data object ChattingRoomListRoute

fun NavController.navigateToChattingRoomList(
    navOptions: NavOptions
) = navigate(route = ChattingRoomListRoute, navOptions)

fun NavGraphBuilder.chattingRoomListScreen(
    navigateToChattingRoom: (String) -> Unit
) {
    composable<ChattingRoomListRoute> {
        ChattingRoomListScreen(
            navigateToChattingRoom = navigateToChattingRoom
        )
    }
}