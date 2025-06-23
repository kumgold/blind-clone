package com.example.blindclone.feature.employment.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.blindclone.feature.employment.EmploymentScreen
import kotlinx.serialization.Serializable

@Serializable data object EmploymentRoute

fun NavController.navigateToEmployment(navOptions: NavOptions) = navigate(route = EmploymentRoute, navOptions)

fun NavGraphBuilder.employmentScreen() {
    composable<EmploymentRoute> {
        EmploymentScreen()
    }
}