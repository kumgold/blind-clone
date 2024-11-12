package net.hicare.officeclone.core.feature.chat.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.hicare.officeclone.core.feature.chat.ChatScreen

@Serializable data object ChatRoute

fun NavController.navigateToChat(navOptions: NavOptions) = navigate(route = ChatRoute, navOptions)

fun NavGraphBuilder.chatScreen(
    onChatClick: () -> Unit
) {
    composable<ChatRoute> {
        ChatScreen(
            onChatClick = onChatClick
        )
    }
}