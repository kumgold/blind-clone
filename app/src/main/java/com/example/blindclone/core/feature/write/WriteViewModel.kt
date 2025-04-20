package com.example.blindclone.core.feature.write

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindclone.core.model.Post
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val database: FirebaseDatabase
) : ViewModel() {
    private val _saveState = MutableStateFlow<SaveState>(SaveState.Idle)
    val saveState: StateFlow<SaveState> = _saveState

    fun savePost(title: String, content: String) {
        if (title.isBlank() || content.isBlank()) {
            _saveState.value = SaveState.Error("제목과 내용을 모두 입력해주세요.")
            viewModelScope.launch {
                kotlinx.coroutines.delay(2000)
                _saveState.value = SaveState.Idle
            }
            return
        }

        _saveState.value = SaveState.Saving

        viewModelScope.launch {
            try {
                val postsRef = database.reference.child("posts")
                val postId = postsRef.push().key

                if (postId == null) {
                    Log.e("WriteViewModel", "Couldn't get push key for posts")
                    _saveState.value = SaveState.Error("데이터 저장에 실패했습니다 (키 생성 오류).")
                    return@launch
                }

                val post = Post(title = title, content = content)

                postsRef.child(postId).setValue(post)
                    .addOnSuccessListener {
                        Log.d("WriteViewModel", "Post saved successfully! ID: $postId")
                        _saveState.value = SaveState.Success
                    }
                    .addOnFailureListener { e ->
                        Log.e("WriteViewModel", "Error saving post", e)
                        _saveState.value = SaveState.Error("데이터 저장 중 오류 발생: ${e.message}") // 실패 상태 알림
                    }
            } catch (e: Exception) {
                Log.e("WriteViewModel", "Exception during savePost", e)
                _saveState.value = SaveState.Error("예외 발생: ${e.message}")
            }
        }
    }

    sealed interface SaveState {
        data object Idle : SaveState
        data object Saving : SaveState
        data object Success : SaveState
        data class Error(val message: String) : SaveState
    }
}