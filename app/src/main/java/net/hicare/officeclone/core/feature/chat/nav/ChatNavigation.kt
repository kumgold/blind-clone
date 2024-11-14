package net.hicare.officeclone.core.feature.chat.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import kotlinx.serialization.Serializable
import net.hicare.officeclone.core.feature.chat.ChatScreen
import net.hicare.officeclone.core.feature.chattingroom.ChattingRoomDialog

@Serializable data object ChatRoute

fun NavController.navigateToChat(
    navOptions: NavOptions
) = navigate(route = ChatRoute, navOptions)

fun NavGraphBuilder.chatScreen() {
    composable<ChatRoute> {
        ChatScreen()
    }
}