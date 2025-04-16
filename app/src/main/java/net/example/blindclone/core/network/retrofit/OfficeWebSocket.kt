package net.example.officeclone.core.network.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfficeWebSocket @Inject constructor() : OfficeWebSocketDataSource {
    companion object {
        private const val WEB_SOCKET = "WebSocket"
    }

    private var webSocket: WebSocket? = null

    private val okhttp = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .pingInterval(30, TimeUnit.SECONDS)
        .build()

    private val listener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)

            println("on open = $response")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)

            println("on message = $text")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)

            println("on closed = $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)

            println("on failure $response")
        }
    }

    override fun connect() {
        Log.d(WEB_SOCKET, "connect()")

        val request = Request.Builder()
            .url("ws://10.0.2.2:8080/chats")
            .build()

        webSocket = okhttp.newWebSocket(request, listener)
    }

    override fun disconnect() {
        Log.d(WEB_SOCKET, "disconnect()")

        webSocket?.cancel()
        webSocket = null
    }

    override fun sendMessage(message: String) {
        val messageJson = JSONObject().apply {
            put("message", message)
        }

        if (webSocket?.send(messageJson.toString()) == true) {
            Log.d("message success", messageJson.toString())
        } else {
            Log.d("message false", messageJson.toString())
        }
    }
}