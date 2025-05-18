package com.example.micafe

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EcpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ecp)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainecp)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //BACK BTN
        val backbtn: ImageButton = findViewById(R.id.backbtn)
        backbtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}