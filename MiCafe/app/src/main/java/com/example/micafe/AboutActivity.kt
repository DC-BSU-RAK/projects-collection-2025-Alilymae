package com.example.micafe

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AboutActivity : AppCompatActivity() {
    private lateinit var menubtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainabout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val visitBtn: ImageButton = findViewById(R.id.visitbtn)
        visitBtn.setOnClickListener {
            val url = "https://micafe.vercel.app/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
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

            homeBtn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                menuWindow.dismiss()
            }

            menuBtn.setOnClickListener {
                val intent = Intent(this, MenuPageActivity::class.java) // Make sure this activity exists
                startActivity(intent)
                menuWindow.dismiss()
            }
        }
    }
}