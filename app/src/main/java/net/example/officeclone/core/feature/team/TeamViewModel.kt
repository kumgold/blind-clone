package net.example.officeclone.core.feature.team

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.example.officeclone.core.network.data.Member
import net.example.officeclone.core.repository.repo.TeamMemberRepository
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val teamMemberRepository: TeamMemberRepository
) : ViewModel() {

    private val _memberList = MutableStateFlow<List<Member>>(listOf())
    val memberList: StateFlow<List<Member>> = _memberList

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch {
            Log.d("TEam member", "${teamMemberRepository.getTeamMembers()}")
            _memberList.value = teamMemberRepository.getTeamMembers()
        }
    }
}