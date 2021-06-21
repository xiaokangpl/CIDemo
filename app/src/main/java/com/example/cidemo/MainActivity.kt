package com.example.cidemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.cidemo.injust.InJustActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_text).setOnClickListener {
            var intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.bt_switch).setOnClickListener {
            var intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.injust).setOnClickListener {
            var intent = Intent(this@MainActivity, InJustActivity::class.java)
            startActivity(intent)
        }

    }
}