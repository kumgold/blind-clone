package com.example.blindclone.core.feature.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.example.blindclone.core.feature.home.HomeScreen


/**
 * reference : https://medium.com/androiddevelopers/type-safe-navigation-for-compose-105325a97657
 */
@Serializable data object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
    navigateToWriteScreen: () -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(
            navigateToWriteScreen = navigateToWriteScreen
        )
    }
}