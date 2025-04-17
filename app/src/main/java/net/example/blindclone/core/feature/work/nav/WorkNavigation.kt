package net.example.blindclone.core.feature.work.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.example.blindclone.core.feature.work.WorkScreen

@Serializable data object WorkRoute

fun NavController.navigateToWork(navOptions: NavOptions) = navigate(route = WorkRoute, navOptions)

fun NavGraphBuilder.workScreen() {
    composable<WorkRoute> {
        WorkScreen()
    }
}