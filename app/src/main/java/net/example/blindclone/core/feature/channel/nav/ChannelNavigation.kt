package net.example.blindclone.core.feature.channel.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.example.blindclone.core.feature.channel.ChannelScreen

@Serializable data object ChannelRoute

fun NavController.navigateToChannel(navOptions: NavOptions) = navigate(route = ChannelRoute, navOptions)

fun NavGraphBuilder.channelScreen() {
    composable<ChannelRoute> {
        ChannelScreen()
    }
}