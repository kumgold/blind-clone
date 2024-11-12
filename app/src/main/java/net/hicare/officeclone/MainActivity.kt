package net.hicare.officeclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import net.hicare.officeclone.core.data.ConnectivityManagerNetworkMonitor
import net.hicare.officeclone.navigation.ui.OfficeApp
import net.hicare.officeclone.navigation.ui.rememberOfficeAppState
import net.hicare.officeclone.ui.theme.OfficeCloneTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkMonitor = ConnectivityManagerNetworkMonitor(applicationContext)

        enableEdgeToEdge()
        setContent {
            val appState = rememberOfficeAppState(networkMonitor = networkMonitor)
            OfficeCloneTheme {
                OfficeApp(appState = appState)
            }
        }
    }
}
