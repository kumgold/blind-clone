package com.example.blindclone.core.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindclone.R
import com.example.blindclone.common.data.Result
import com.example.blindclone.core.model.Post
import com.example.blindclone.core.repository.repo.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeUiState(
    val posts: List<Post> = listOf(),
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
        postRepository.getPosts(),
        _isLoading,
        _message
    ) { postsResult, isLoading, message ->

        val isLoadingCombined = postsResult is Result.Loading || isLoading

        var messageCombined: Int? = message
        if (messageCombined == null) {
            messageCombined = when {
                postsResult is Result.Error -> R.string.network_error
                else -> null
            }
        }

        val postsList = (postsResult as? Result.Success)?.data ?: emptyList()

        HomeUiState(
            posts = postsList,
            isLoading = isLoadingCombined,
            message = messageCombined
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState(isLoading = true)
        )
}