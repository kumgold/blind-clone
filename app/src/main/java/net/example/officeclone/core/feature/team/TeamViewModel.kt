package net.example.officeclone.core.feature.team

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.example.officeclone.core.network.data.User
import net.example.officeclone.core.repository.repo.TeamMemberRepository
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val teamMemberRepository: TeamMemberRepository
) : ViewModel() {

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch {
            Log.d("TEam member", "${teamMemberRepository.getTeamMembers()}")
        }
    }

    val userList = listOf(
        User(
            id = "1",
            name = "김김김",
            number = "01011111111",
            message = "test1"
        ),
        User(
            id = "2",
            name = "김김김2",
            number = "01011111111",
            message = "message"
        ),
        User(
            id = "3",
            name = "별명",
            number = "01012341211",
            message = "안녕하세요"
        ),
        User(
            id = "4",
            name = "dldldl",
            number = "01012311111",
            message = "status message"
        ),
        User(
            id = "5",
            name = "test test",
            number = "01011111111",
            message = "status message"
        ),
    )
}