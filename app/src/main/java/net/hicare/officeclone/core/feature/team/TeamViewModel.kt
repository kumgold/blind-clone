package net.hicare.officeclone.core.feature.team

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import net.hicare.officeclone.core.network.User
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor() : ViewModel() {
    val userList = listOf(
        User(
            id = "1",
            name = "김김김",
            number = "01012312311",
            message = "status message",
            group = "Group1",
            isFavorite = true
        ),
        User(
            id = "2",
            name = "김김김2",
            number = "01011111111",
            message = "status",
            group = "Group2",
            isFavorite = false
        ),
        User(
            id = "3",
            name = "김김김",
            number = "01012312311",
            message = "status message",
            group = "Group",
            isFavorite = false
        ),
        User(
            id = "4",
            name = "test test",
            number = "01012312311",
            message = "test",
            group = "Group",
            isFavorite = false
        ),
        User(
            id = "5",
            name = "name1",
            number = "01012312311",
            message = "status message",
            group = "Group",
            isFavorite = true
        ),
    )
}