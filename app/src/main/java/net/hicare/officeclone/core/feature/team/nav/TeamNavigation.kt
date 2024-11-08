package net.hicare.officeclone.core.feature.team.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.hicare.officeclone.core.feature.team.TeamScreen

@Serializable data object TeamRoute

fun NavController.navigateToTeam(navOptions: NavOptions) = navigate(route = TeamRoute, navOptions)

fun NavGraphBuilder.teamScreen() {
    composable<TeamRoute> {
        TeamScreen()
    }
}