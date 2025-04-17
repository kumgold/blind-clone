package net.example.blindclone.core.feature.chattingroom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.example.blindclone.core.feature.chattingroom.nav.ChattingRoomRoute
import net.example.blindclone.core.repository.repo.ChatRepository
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

    val chatList = chatRepository.getChatList(chattingRoomId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    private fun sync() {
        val chattingRoomId = savedStateHandle.toRoute<ChattingRoomRoute>().chattingRoomId

        viewModelScope.launch {
            chatRepository.sync(chattingRoomId)
        }
    }

    // 대화 상대 정보

    // 채팅 리스트

    // 채팅 보내기
    fun sendChat(message: String) {
        val memberId = chattingRoomId.split("-").last()

        viewModelScope.launch {
            chatRepository.sendChat(message, memberId, chattingRoomId)
        }
    }
}