package net.example.blindclone.core.feature.corporation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.example.blindclone.core.repository.repo.ChattingRoomRepository
import javax.inject.Inject

@HiltViewModel
class CorporationViewModel @Inject constructor(
    private val chattingRoomRepository: ChattingRoomRepository
) : ViewModel() {

    val chattingRoomList = chattingRoomRepository.getChattingRooms()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    init {
        sync()
    }

    private fun sync() {
        viewModelScope.launch {
            chattingRoomRepository.sync()
        }
    }
}