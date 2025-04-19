package com.example.blindclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import com.example.blindclone.core.data.ConnectivityManagerNetworkMonitor
import com.example.blindclone.navigation.BlindApp
import com.example.blindclone.navigation.rememberBlindAppState
import com.example.blindclone.ui.theme.BlindCloneTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkMonitor = ConnectivityManagerNetworkMonitor(applicationContext)

        enableEdgeToEdge()
        setContent {
            val appState = rememberBlindAppState(networkMonitor = networkMonitor)
            BlindCloneTheme {
                BlindApp(appState = appState)
            }
        }
    }
}
