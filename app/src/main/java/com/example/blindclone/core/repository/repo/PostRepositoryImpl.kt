package com.example.blindclone.core.repository.repo

import android.util.Log
import com.example.blindclone.common.data.Result
import com.example.blindclone.core.model.Post
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
) : PostRepository {

    override fun getPosts(): Flow<Result<List<Post>>> = callbackFlow {
        val postsRef = database.reference.child("posts")

        trySend(Result.Loading)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postsList = mutableListOf<Post>()
                if (snapshot.exists()) {
                    for (postSnapshot in snapshot.children) {
                        val post = postSnapshot.getValue(Post::class.java)

                        post?.let {
                            postsList.add(it)
                        }
                    }
                    trySend(Result.Success(postsList.reversed()))
                } else {
                    trySend(Result.Success(emptyList()))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("PostRepositoryImpl", "Firebase getPosts onCancelled: ${error.message}")
                trySend(Result.Error(error.toException()))
                close(error.toException())
            }
        }

        postsRef.addValueEventListener(listener)

        // Flow가 취소될 때 리스너 제거, 메모리 누수 방지
        awaitClose {
            Log.d("PostRepositoryImpl", "Removing Firebase posts listener")
            postsRef.removeEventListener(listener)
        }
    }
        .catch { e ->
            Log.e("PostRepositoryImpl", "Exception in getPosts flow", e)
            emit(Result.Error(Exception("Flow error fetching posts", e)))
        }
        .flowOn(Dispatchers.IO)
}