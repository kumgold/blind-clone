package com.example.blindclone.feature.home.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.blindclone.feature.home.HomeScreen
import kotlinx.serialization.Serializable


/**
 * reference : https://medium.com/androiddevelopers/type-safe-navigation-for-compose-105325a97657
 */
@Serializable data object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
    navController: NavController,
    navigateToWriteScreen: () -> Unit,
    navigateToPostDetail: (String) -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(
            navController = navController,
            navigateToWriteScreen = navigateToWriteScreen,
            navigateToPostDetail = navigateToPostDetail
        )
    }
}