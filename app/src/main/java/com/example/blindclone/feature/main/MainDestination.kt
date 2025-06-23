package com.example.blindclone.feature.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Domain
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.blindclone.R
import com.example.blindclone.feature.channel.nav.ChannelRoute
import com.example.blindclone.feature.corporation.nav.CorporationRoute
import com.example.blindclone.feature.employment.nav.EmploymentRoute
import com.example.blindclone.feature.home.nav.HomeRoute
import com.example.blindclone.feature.notification.nav.NotificationRoute
import kotlin.reflect.KClass

enum class MainDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    val route: KClass<*>
) {
    HOME(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.home,
        route = HomeRoute::class
    ),
    CORPORATION(
        selectedIcon = Icons.Filled.Domain,
        unselectedIcon = Icons.Outlined.Domain,
        iconTextId = R.string.corporation,
        route = CorporationRoute::class
    ),
    CHANNEL(
        selectedIcon = Icons.Filled.GridView,
        unselectedIcon = Icons.Outlined.GridView,
        iconTextId = R.string.channel,
        route = ChannelRoute::class
    ),
    EMPLOYMENT(
        selectedIcon = Icons.Filled.Work,
        unselectedIcon = Icons.Outlined.WorkOutline,
        iconTextId = R.string.employment,
        route = EmploymentRoute::class
    ),
    NOTIFICATION(
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications,
        iconTextId = R.string.notification,
        route = NotificationRoute::class
    )
}