package net.hicare.officeclone.navigation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import net.hicare.officeclone.core.design.OfficeNavigationSuiteScaffold
import net.hicare.officeclone.navigation.nav.OfficeNavHost

@Composable
internal fun OfficeApp(
    modifier: Modifier = Modifier,
    appState: OfficeAppState
) {
    val currentDestination = appState.currentDestination

    OfficeNavigationSuiteScaffold(
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach { destination ->
                val selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(destination.route)
                } ?: false

                item(
                    selected = selected,
                    onClick = {
                        appState.navigateToTopLevelDestination(destination)
                    },
                    icon = {
                        Icon(imageVector = destination.unselectedIcon, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = stringResource(id = destination.iconTextId))}
                )
            }
        }
    ) {
        Scaffold(
            modifier = modifier
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                OfficeNavHost(appState = appState)
            }
        }
    }
}