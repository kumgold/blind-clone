package net.example.officeclone.core.feature.chattingroom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.example.officeclone.core.feature.chattingroom.nav.ChattingRoomRoute
import net.example.officeclone.core.repository.repo.ChatRepository
import javax.inject.Inject

@HiltViewModel
class ChattingRoomViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val chatRepository: ChatRepository
) : ViewModel() {

    init {
        sync()
    }

    private val chattingRoomId = savedStateHandle.toRoute<ChattingRoomRoute>().chattingRoomId

    val chatList = chatRepository.getChatList("1-2")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    private fun sync() {
        viewModelScope.launch {
            chatRepository.sync(chattingRoomId)
        }
    }
}