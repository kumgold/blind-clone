package com.example.blindclone.core.network.retrofit

interface BlindWebSocketDataSource {
    fun connect()
    fun disconnect()
    fun sendMessage(message: String)
}