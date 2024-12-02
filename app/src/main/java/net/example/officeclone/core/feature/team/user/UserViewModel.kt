package net.example.officeclone.core.feature.team.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.example.officeclone.core.model.ChattingRoom
import net.example.officeclone.core.repository.repo.ChattingRoomRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val chattingRoomRepository: ChattingRoomRepository
) : ViewModel() {

    fun createChattingRoom(id: String, name: String) {
        viewModelScope.launch {
            chattingRoomRepository.createChattingRoom(
                ChattingRoom(
                    id = "1-$id",
                    name = name,
                    previewMessage = "",
                    memberCount = 2
                )
            )
        }
    }
}