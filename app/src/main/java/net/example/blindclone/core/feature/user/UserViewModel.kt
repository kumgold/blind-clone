package net.example.blindclone.core.feature.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.example.blindclone.R
import net.example.blindclone.core.model.ChattingRoom
import net.example.blindclone.core.repository.repo.ChattingRoomRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val chattingRoomRepository: ChattingRoomRepository
) : ViewModel() {

    private val _message: MutableStateFlow<Int?> = MutableStateFlow(null)
    val message: StateFlow<Int?> = _message

    fun createChattingRoom(
        id: String,
        name: String,
        memberIdList: List<String>
    ) {
        viewModelScope.launch {
            try {
                val created = chattingRoomRepository.createChattingRoom(
                    ChattingRoom(
                        id = id,
                        name = name,
                        memberIdList = memberIdList
                    )
                )

                if (created.isFailure) {
                    _message.value = R.string.network_error
                }
            } catch (e: Exception) {
                _message.value = R.string.network_error
            }
        }
    }
}