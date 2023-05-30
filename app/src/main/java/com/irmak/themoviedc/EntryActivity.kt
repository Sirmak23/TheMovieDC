package com.irmak.themoviedc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlin.concurrent.thread

class EntryActivity : AppCompatActivity() {

    private val delayMillis: Long = 3500
    private val delayMillisText: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        val entryText = findViewById<TextView>(R.id.textViewEntry)
        val entry = findViewById<ImageView>(R.id.entryImage)
        entryText.visibility = View.GONE
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_entry)
        entry.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({
            entryText.visibility = View.VISIBLE
            val textAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_entry_text)
            entryText.startAnimation(textAnimation)
        }, delayMillisText)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}
