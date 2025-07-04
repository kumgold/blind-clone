package com.example.blindclone.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.blindclone.feature.channel.nav.channelScreen
import com.example.blindclone.feature.corporation.nav.corporationScreen
import com.example.blindclone.feature.employment.nav.employmentScreen
import com.example.blindclone.feature.home.nav.HomeRoute
import com.example.blindclone.feature.home.nav.homeScreen
import com.example.blindclone.feature.login.LoginScreen
import com.example.blindclone.feature.main.MainScreen
import com.example.blindclone.feature.notification.nav.notificationScreen
import com.example.blindclone.feature.postdetail.PostDetailScreen
import com.example.blindclone.feature.write.WriteScreen
import kotlinx.serialization.Serializable

@Composable
internal fun BlindApp(
    modifier: Modifier = Modifier,
    appState: BlindAppState,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        val navController = appState.navController
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination
        val isMainSection = currentDestination?.hierarchy?.any { it.route == RootRoute.Main::class.qualifiedName || it.route == RootRoute.Main.toString() } ?: false

        if (isMainSection) {
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                appState = appState,
                windowAdaptiveInfo = windowAdaptiveInfo,
                content = {
                    RootNavHost(
                        appState = appState
                    )
                }
            )
        } else {
            RootNavHost(
                modifier = Modifier.fillMaxSize(),
                appState = appState
            )
        }
    }
}

sealed class RootRoute {
    @Serializable data object Login: RootRoute()
    @Serializable data object Write: RootRoute()
    @Serializable data object Main: RootRoute()
    @Serializable data object PostDetail: RootRoute()
}

@Composable
fun RootNavHost(
    modifier: Modifier = Modifier,
    appState: BlindAppState
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RootRoute.Login
    ) {
        composable<RootRoute.Login> {
            LoginScreen(
                navigateToMain = {
                    navController.navigate(RootRoute.Main)
                }
            )
        }
        composable<RootRoute.Write> {
            WriteScreen(
                popBackStack = { navController.popBackStack() }
            )
        }
        composable(
            route = "${RootRoute.PostDetail}/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.StringType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
            if (postId != null) {
                PostDetailScreen(
                    postId = postId,
                    popBackStack = { navController.popBackStack() }
                )
            }
        }

        navigation<RootRoute.Main>(startDestination = HomeRoute) {
            homeScreen(
                navController = navController,
                navigateToWriteScreen = { navController.navigate(RootRoute.Write) },
                navigateToPostDetail = { id -> navController.navigate("${RootRoute.PostDetail}/$id")}
            )
            corporationScreen(
                navController = navController
            )
            channelScreen(
                navController = navController
            )
            employmentScreen()
            notificationScreen(
                navController = navController
            )
        }
    }
}
