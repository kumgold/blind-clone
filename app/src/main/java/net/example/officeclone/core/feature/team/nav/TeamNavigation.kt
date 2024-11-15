package net.example.officeclone.core.feature.team.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.example.officeclone.core.feature.team.TeamScreen


/**
 * reference : https://medium.com/androiddevelopers/type-safe-navigation-for-compose-105325a97657
 */
@Serializable data object TeamRoute

fun NavController.navigateToTeam(navOptions: NavOptions) = navigate(route = TeamRoute, navOptions)

fun NavGraphBuilder.teamScreen(
    navigateToChattingRoom: (String) -> Unit
) {
    composable<TeamRoute> {
        TeamScreen(
            navigateToChattingRoom = navigateToChattingRoom
        )
    }
}