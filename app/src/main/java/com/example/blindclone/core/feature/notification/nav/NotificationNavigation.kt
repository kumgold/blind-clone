package com.example.blindclone.core.feature.notification.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.example.blindclone.core.feature.notification.NotificationScreen

/**
 * reference : https://medium.com/androiddevelopers/type-safe-navigation-for-compose-105325a97657
 */
@Serializable
data object NotificationRoute

fun NavController.navigateToNotification(navOptions: NavOptions) = navigate(route = NotificationRoute, navOptions)

fun NavGraphBuilder.notificationScreen(
    navController: NavController
) {
    composable<NotificationRoute> {
        NotificationScreen(
            navController = navController
        )
    }
}