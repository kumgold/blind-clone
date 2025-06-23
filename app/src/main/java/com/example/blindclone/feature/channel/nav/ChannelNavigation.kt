package com.example.blindclone.feature.channel.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.blindclone.feature.channel.ChannelScreen
import kotlinx.serialization.Serializable

@Serializable data object ChannelRoute

fun NavController.navigateToChannel(navOptions: NavOptions) = navigate(route = ChannelRoute, navOptions)

fun NavGraphBuilder.channelScreen(
    navController: NavController
) {
    composable<ChannelRoute> {
        ChannelScreen(
            navController = navController
        )
    }
}