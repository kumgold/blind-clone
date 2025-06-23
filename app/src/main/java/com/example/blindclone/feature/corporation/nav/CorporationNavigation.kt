package com.example.blindclone.feature.corporation.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.blindclone.feature.corporation.CorporationScreen
import kotlinx.serialization.Serializable

@Serializable data object CorporationRoute

fun NavController.navigateToCorporation(
    navOptions: NavOptions
) = navigate(route = CorporationRoute, navOptions)

fun NavGraphBuilder.corporationScreen(
    navController: NavController
) {
    composable<CorporationRoute> {
        CorporationScreen(
            navController = navController
        )
    }
}