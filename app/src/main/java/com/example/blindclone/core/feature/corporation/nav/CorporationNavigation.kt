package com.example.blindclone.core.feature.corporation.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.example.blindclone.core.feature.corporation.CorporationScreen

@Serializable data object CorporationRoute

fun NavController.navigateToCorporation(
    navOptions: NavOptions
) = navigate(route = CorporationRoute, navOptions)

fun NavGraphBuilder.corporationScreen() {
    composable<CorporationRoute> {
        CorporationScreen()
    }
}