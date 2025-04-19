package com.example.blindclone.core.feature.main

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.blindclone.navigation.BlindAppState
import com.example.blindclone.ui.component.BlindNavigationSuiteScaffold
import kotlin.reflect.KClass

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    appState: BlindAppState,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit
) {
    val currentDestination = appState.currentDestination

    BlindNavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            appState.mainDestinations.forEach { destination ->
                val selected = currentDestination.isRouteInHierarchy(destination.route)

                item(
                    selected = selected,
                    onClick = {
                        appState.navigateToMainDestination(destination)
                    },
                    icon = {
                        Icon(imageVector = destination.unselectedIcon, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = destination.name
                        )
                    },
                    label = { Text(text = stringResource(id = destination.iconTextId)) }
                )
            }
        },
        windowAdaptiveInfo = windowAdaptiveInfo
    ) {
        content()
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false