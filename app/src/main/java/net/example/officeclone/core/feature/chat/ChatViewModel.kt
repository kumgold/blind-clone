package net.example.officeclone.core.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.example.officeclone.core.repository.repo.ChattingRoomRepository
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chattingRoomRepository: ChattingRoomRepository
) : ViewModel() {

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch {
            chattingRoomRepository.getChattingRooms()
        }
    }

    fun sync() {
        viewModelScope.launch {
            chattingRoomRepository.sync()
        }
    }
}