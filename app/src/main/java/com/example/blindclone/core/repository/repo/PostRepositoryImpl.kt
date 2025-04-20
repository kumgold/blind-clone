package com.example.blindclone.core.repository.repo

import android.util.Log
import kotlinx.coroutines.flow.Flow
import com.example.blindclone.common.data.Result
import com.example.blindclone.core.database.dao.MemberDao
import com.example.blindclone.core.model.Post
import com.example.blindclone.core.network.model.NetworkMember
import com.example.blindclone.core.network.model.asEntity
import com.example.blindclone.core.network.retrofit.BlindNetworkDataSource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
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
                        // 각 자식 노드(개별 포스트)를 Post 객체로 변환
                        val post = postSnapshot.getValue(Post::class.java)
                        // 키(ID)도 필요하다면 여기서 추출 가능: postSnapshot.key
                        post?.let {
                            // 필요하다면 id 필드에 키 할당
                            // val postWithId = it.copy(id = postSnapshot.key ?: "")
                            postsList.add(it)
                        }
                    }
                    // 성공 상태와 함께 리스트 방출
                    trySend(Result.Success(postsList.reversed()))
                } else {
                    // 데이터가 없는 경우 빈 리스트로 성공 처리
                    trySend(Result.Success(emptyList()))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("PostRepositoryImpl", "Firebase getPosts onCancelled: ${error.message}")
                // 에러 상태 방출 및 플로우 종료
                trySend(Result.Error(error.toException()))
                close(error.toException())
            }
        }

        // 리스너 등록
        postsRef.addValueEventListener(listener)

        // Flow가 취소될 때 리스너 제거 (메모리 누수 방지)
        awaitClose {
            Log.d("PostRepositoryImpl", "Removing Firebase posts listener")
            postsRef.removeEventListener(listener)
        }
    }
        .catch { e -> // Flow 자체에서 발생하는 예외 처리
            Log.e("PostRepositoryImpl", "Exception in getPosts flow", e)
            emit(Result.Error(Exception("Flow error fetching posts", e)))
        }
        .flowOn(Dispatchers.IO)
}