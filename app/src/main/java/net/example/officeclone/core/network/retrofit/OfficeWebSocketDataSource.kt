package net.example.officeclone.core.network.retrofit

interface OfficeWebSocketDataSource {
    fun connect()
    fun disconnect()
    fun sendMessage(message: String)
}