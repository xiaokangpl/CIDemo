package com.example.cidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://www.youtube.com/embed/JlumJtcytFk"
        val videoView = findViewById<VideoView>(R.id.video_view)
        videoView.setVideoPath(url)
        videoView.requestFocus()
        videoView.start()

    }
}