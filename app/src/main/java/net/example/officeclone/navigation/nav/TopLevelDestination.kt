package net.example.officeclone.navigation.nav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.ui.graphics.vector.ImageVector
import net.example.officeclone.R
import net.example.officeclone.core.feature.chat.nav.ChatRoute
import net.example.officeclone.core.feature.settings.nav.SettingsRoute
import net.example.officeclone.core.feature.team.nav.TeamRoute
import net.example.officeclone.core.feature.work.nav.WorkRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    val route: KClass<*>
) {
    TEAM(
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Outlined.PersonOutline,
        iconTextId = R.string.team,
        route = TeamRoute::class
    ),
    CHAT(
        selectedIcon = Icons.Default.ChatBubble,
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        iconTextId = R.string.chat,
        route = ChatRoute::class
    ),
    WORK(
        selectedIcon = Icons.Default.Work,
        unselectedIcon = Icons.Outlined.WorkOutline,
        iconTextId = R.string.work,
        route = WorkRoute::class
    ),
    SETTINGS(
        selectedIcon = Icons.Default.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        iconTextId = R.string.settings,
        route = SettingsRoute::class
    )
}