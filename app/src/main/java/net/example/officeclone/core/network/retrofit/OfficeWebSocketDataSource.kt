package net.example.officeclone.core.network.retrofit

import okhttp3.WebSocketListener

interface OfficeWebSocketDataSource {
    fun connect(listener: WebSocketListener)
    fun disconnect()
    fun sendMessage(message: String)
}