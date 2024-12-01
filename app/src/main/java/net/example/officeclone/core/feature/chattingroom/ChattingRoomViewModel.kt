package net.example.officeclone.core.feature.chattingroom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.example.officeclone.core.repository.repo.ChatRepository
import javax.inject.Inject

@HiltViewModel
class ChattingRoomViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val chatRepository: ChatRepository
) : ViewModel() {

    init {
        test()
    }

    private fun test() {
        viewModelScope.launch {
            chatRepository.getChatList("1-2")
                .collectLatest {
                    println(it)
                }
        }
    }
}