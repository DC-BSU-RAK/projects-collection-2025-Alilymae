package com.example.micafe

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class colddrinks : AppCompatActivity() {
    private lateinit var menubtn: ImageButton
    private lateinit var tvGreeting: TextView
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_colddrinks)

        tvGreeting = findViewById(R.id.usergreet)
        sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val username = sharedPref.getString("username", "User")
        tvGreeting.text = "Hello, $username!"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.maincolddrinks)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //CATEGORIES BUTTONS
        val hotdrinksbtn: ImageButton = findViewById(R.id.hotdrinksbtn)
        val colddrinkstbtn: ImageButton = findViewById(R.id.colddrinksbtn)
        val desertbtn: ImageButton = findViewById(R.id.desertbtn)
        val pastriesbtn: ImageButton = findViewById(R.id.pastriesbtn)

        hotdrinksbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        colddrinkstbtn.setOnClickListener {
            val intent = Intent(this, colddrinks::class.java)
            startActivity(intent)
        }

        desertbtn.setOnClickListener {
            val intent = Intent(this, DesertActivity::class.java)
            startActivity(intent)
        }

        pastriesbtn.setOnClickListener {
            val intent = Intent( this, PastriesActivity::class.java)
            startActivity(intent)
        }

        menubtn = findViewById(R.id.menubtn)
        menubtn.setOnClickListener {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.menu, null)
            val menuWindow = PopupWindow(popupView, 700, 2600, true)
            menuWindow.showAtLocation(popupView, Gravity.START, 0, 0)

            val btnClose: ImageButton = popupView.findViewById(R.id.menubackbtn)
            btnClose.setOnClickListener {
                menuWindow.dismiss()
            }

            val homeBtn: ImageButton = popupView.findViewById(R.id.hometextbtn)
            val menuBtn: ImageButton = popupView.findViewById(R.id.menutextbtn)
            val aboutBtn: ImageButton = popupView.findViewById(R.id.abouttextbtn)

            homeBtn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                menuWindow.dismiss()
            }

            menuBtn.setOnClickListener {
                val intent = Intent(this, MenuPageActivity::class.java)
                startActivity(intent)
                menuWindow.dismiss()
            }

            aboutBtn.setOnClickListener {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                menuWindow.dismiss()
            }
        }
    }
}