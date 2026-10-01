package com.example.radiostreaming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.radiostreaming.player.RadioPlayerManager
import com.example.radiostreaming.ui.MainApp
import com.example.radiostreaming.ui.theme.RadioStreamingTheme

class MainActivity : ComponentActivity() {

    private lateinit var playerManager: RadioPlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playerManager = RadioPlayerManager(this)

        setContent {
            RadioStreamingTheme {
                MainApp(playerManager = playerManager)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerManager.release()
    }
}
