package com.example.blindclone.feature.postdetail

import android.util.Log
import androidx.compose.animation.core.snap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blindclone.core.model.Post
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val database: FirebaseDatabase
) : ViewModel() {
    private val _selectedItem = MutableStateFlow<Post?>(null)
    val selectedItem: StateFlow<Post?> = _selectedItem

    fun fetchItemById(id: String) {
        val databaseRef = database.reference.child("posts")

        viewModelScope.launch {
            try {
                _selectedItem.value = null

                val snapshot = databaseRef.child(id).get().await()
                val item = snapshot.getValue(Post::class.java)
                if (item != null) {
                    _selectedItem.value = item.copy(id = snapshot.key!!)
                }
            } catch (e: Exception) {
                Log.e("WriteViewModel", "Exception during fetchPost", e)
            }
        }
    }
}