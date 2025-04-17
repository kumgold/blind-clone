package net.example.blindclone.core.network.retrofit

interface OfficeWebSocketDataSource {
    fun connect()
    fun disconnect()
    fun sendMessage(message: String)
}