package com.example.radiostreaming.player

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.radiostreaming.model.RadioStation

class RadioPlayerManager(context: Context) {

    private val player: ExoPlayer = ExoPlayer.Builder(context).build()

    var currentStation by mutableStateOf<RadioStation?>(null)
        private set

    var isPlaying by mutableStateOf(false)
        private set

    var isBuffering by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(playing: Boolean) {
                isPlaying = playing
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        isBuffering = true
                        errorMessage = null
                    }
                    Player.STATE_READY -> {
                        isBuffering = false
                        errorMessage = null
                    }
                    Player.STATE_ENDED -> {
                        isBuffering = false
                        isPlaying = false
                    }
                    Player.STATE_IDLE -> {
                        isBuffering = false
                    }
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                isBuffering = false
                isPlaying = false
                errorMessage = "Gagal memutar stream: ${error.localizedMessage ?: "Koneksi bermasalah"}"
            }
        })
    }

    fun play(station: RadioStation) {
        if (currentStation?.id == station.id && player.playbackState != Player.STATE_IDLE) {
            if (isPlaying) {
                player.pause()
            } else {
                player.play()
            }
            return
        }

        currentStation = station
        errorMessage = null
        isBuffering = true

        val mediaItem = MediaItem.fromUri(station.streamUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    fun togglePlayPause() {
        if (isPlaying) {
            player.pause()
        } else {
            if (player.playbackState == Player.STATE_IDLE && currentStation != null) {
                play(currentStation!!)
            } else {
                player.play()
            }
        }
    }

    fun stop() {
        player.stop()
        isPlaying = false
        isBuffering = false
    }

    fun release() {
        player.release()
    }
}
