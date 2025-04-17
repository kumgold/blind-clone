package net.example.blindclone.core.feature.employment.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import net.example.blindclone.core.feature.employment.EmploymentScreen

@Serializable data object EmploymentRoute

fun NavController.navigateToEmployment(navOptions: NavOptions) = navigate(route = EmploymentRoute, navOptions)

fun NavGraphBuilder.employmentScreen() {
    composable<EmploymentRoute> {
        EmploymentScreen()
    }
}