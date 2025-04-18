package com.example.blindclone.core.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindclone.R
import com.example.blindclone.core.model.Member
import com.example.blindclone.core.repository.repo.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.blindclone.common.data.Result
import javax.inject.Inject

data class HomeUiState(
    val members: List<Member> = listOf(),
    val isLoading: Boolean = false,
    val message: Int? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _message = MutableStateFlow<Int?>(null)

    val uiState: StateFlow<HomeUiState> = combine(
        postRepository.getTeamMembers(),
        _isLoading,
        _message
    ) { memberList, isLoading, message ->
        when (memberList) {
            Result.Loading -> {
                HomeUiState(isLoading = true)
            }
            is Result.Error -> {
                HomeUiState(
                    isLoading = false,
                    message = R.string.network_error
                )
            }
            is Result.Success -> {
                HomeUiState(
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
            initialValue = HomeUiState(isLoading = true)
        )

    init {
        sync()
    }

    private fun sync() {
        viewModelScope.launch {
            postRepository.sync()
        }
    }
}