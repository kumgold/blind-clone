package net.example.blindclone.core.feature.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.example.blindclone.R
import net.example.blindclone.core.model.Member
import net.example.blindclone.core.repository.repo.TeamMemberRepository
import net.example.blindclone.common.data.Result
import javax.inject.Inject

data class TeamUiState(
    val members: List<Member> = listOf(),
    val isLoading: Boolean = false,
    val message: Int? = null
)

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val teamMemberRepository: TeamMemberRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _message = MutableStateFlow<Int?>(null)

    val uiState: StateFlow<TeamUiState> = combine(
        teamMemberRepository.getTeamMembers(),
        _isLoading,
        _message
    ) { memberList, isLoading, message ->
        when (memberList) {
            Result.Loading -> {
                TeamUiState(isLoading = true)
            }
            is Result.Error -> {
                TeamUiState(
                    isLoading = false,
                    message = R.string.network_error
                )
            }
            is Result.Success -> {
                TeamUiState(
                    members = memberList.data,
                    isLoading = isLoading,
                    message = message
                )
            }
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TeamUiState(isLoading = true)
        )

    init {
        sync()
    }

    private fun sync() {
        viewModelScope.launch {
            teamMemberRepository.sync()
        }
    }

    // 내 정보 불러오기

    // 내 정보 저장하기

}