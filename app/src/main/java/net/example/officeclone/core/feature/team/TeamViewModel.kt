package net.example.officeclone.core.feature.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import net.example.officeclone.R
import net.example.officeclone.common.data.Result
import net.example.officeclone.common.data.asResult
import net.example.officeclone.core.model.Member
import net.example.officeclone.core.repository.repo.TeamMemberRepository
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
        teamMemberRepository.getTeamMembers().asResult(),
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

    fun createChattingRoom(id: String) {

    }
}