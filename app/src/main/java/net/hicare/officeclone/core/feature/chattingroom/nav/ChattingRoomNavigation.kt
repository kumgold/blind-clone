package net.hicare.officeclone.core.feature.chattingroom.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import kotlinx.serialization.Serializable
import net.hicare.officeclone.core.feature.chattingroom.ChattingRoomDialog

@Serializable
data class ChattingRoomRoute(
    val chattingRoomId: String
)

fun NavController.navigateToChattingRoom(
    chattingRoomId: String
) = navigate(route = ChattingRoomRoute(chattingRoomId))

fun NavGraphBuilder.chattingRoom(
    onDismiss: () -> Unit
) {
    dialog<ChattingRoomRoute> {
        ChattingRoomDialog(
            onDismiss = onDismiss
        )
    }
}