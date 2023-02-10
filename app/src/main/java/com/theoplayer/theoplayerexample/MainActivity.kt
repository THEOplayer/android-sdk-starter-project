package com.theoplayer.theoplayerexample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.theoplayer.android.api.THEOplayerView
import com.theoplayer.android.api.event.player.PlayerEventTypes
import com.theoplayer.android.api.source.SourceDescription
import com.theoplayer.android.api.source.SourceType
import com.theoplayer.android.api.source.TypedSource

class MainActivity : AppCompatActivity() {
    private lateinit var theoPlayerView: THEOplayerView
    private lateinit var btnPlayPause: Button
    private lateinit var txtPlayStatus: TextView
    private lateinit var txtTimeUpdate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        theoPlayerView = findViewById(R.id.theoplayer)

        theoPlayerView.let { view ->
            view.settings.isFullScreenOrientationCoupled = true
            val typedSource = TypedSource.Builder("https://cdn.theoplayer.com/video/dash/big_buck_bunny/BigBuckBunny_10s_simple_2014_05_09.mpd")
                .type(SourceType.DASH)
                .build()
            val sourceDescription = SourceDescription.Builder(typedSource)
                .build()
            view.player.source = sourceDescription
        }

        btnPlayPause = findViewById(R.id.btn_playpause)
        btnPlayPause.setOnClickListener {
            theoPlayerView.player.let { player ->
                if (player.isPaused) {
                    player.play()
                } else {
                    player.pause()
                }
            }
        }
        txtPlayStatus = findViewById(R.id.txt_playstatus)
        txtTimeUpdate = findViewById(R.id.txt_timeupdate)

        // Add player event listeners
        theoPlayerView.player.let { player ->
            player.addEventListener(PlayerEventTypes.PLAY) { txtPlayStatus.text = getText(R.string.playing) }
            player.addEventListener(PlayerEventTypes.PAUSE) { txtPlayStatus.text = getText(R.string.paused) }
            player.addEventListener(PlayerEventTypes.TIMEUPDATE) { timeUpdateEvent ->
                txtTimeUpdate.text = timeUpdateEvent.currentTime.toString()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        theoPlayerView.onPause()
    }

    override fun onResume() {
        super.onResume()
        theoPlayerView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        theoPlayerView.onDestroy()
    }
}