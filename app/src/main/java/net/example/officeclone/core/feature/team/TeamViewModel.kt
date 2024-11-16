package net.example.officeclone.core.feature.team

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.example.officeclone.core.network.data.Member
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

    val memberLists = listOf(
        Member(
            id = "1",
            name = "김김김",
            number = "01011111111",
            statusMessage = "test1"
        ),
        Member(
            id = "2",
            name = "김김김2",
            number = "01011111111",
            statusMessage = "message"
        ),
        Member(
            id = "3",
            name = "별명",
            number = "01012341211",
            statusMessage = "안녕하세요"
        ),
        Member(
            id = "4",
            name = "dldldl",
            number = "01012311111",
            statusMessage = "status message"
        ),
        Member(
            id = "5",
            name = "test test",
            number = "01011111111",
            statusMessage = "status message"
        ),
    )
}