package com.example.wafflicious

import android.media.MediaPlayer
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //EDITABLE LIST FOR INGR
    private var selectedIds = mutableListOf<Int>()
    //VARIABLES
    private lateinit var selected1: ImageView
    private lateinit var selected2: ImageView
    private lateinit var resultImage: ImageView
    private lateinit var btnAdd: ImageButton
    private lateinit var btnClear: ImageButton
    private lateinit var btnInstruct: ImageButton
    //MUSIC
    private lateinit var mediaPlayer: MediaPlayer

    //LIST OF INGR IDS FOR LATER
    private val ingredientButtonIds = listOf(
        R.id.btnWaffle, R.id.btnStrawberry, R.id.btnGrape,
        R.id.btnEgg, R.id.btnCheese, R.id.btnBlueberry,
        R.id.btnCookie, R.id.btnHoney, R.id.btnBanana,
        R.id.btnCake, R.id.btnCreamcheese, R.id.btnsyrup,
        R.id.btnicecream
    )

    //THE FOOD COMBOS THAT ARE AVAILABLE
    private val combinationMap = mapOf(
        setOf(R.id.btnWaffle, R.id.btnStrawberry) to R.drawable.strawberry_waffle,
        setOf(R.id.btnWaffle, R.id.btnGrape) to R.drawable.grape_waffle,
        setOf(R.id.btnWaffle, R.id.btnBlueberry) to R.drawable.blueberry_waffle,
        setOf(R.id.btnWaffle, R.id.btnHoney) to R.drawable.honey_waffle,
        setOf(R.id.btnWaffle, R.id.btnCreamcheese) to R.drawable.creamcheese_waffle,
        setOf(R.id.btnWaffle, R.id.btnBanana) to R.drawable.banana_waffle,
        setOf(R.id.btnWaffle, R.id.btnCheese) to R.drawable.cheese_waffle,
        setOf(R.id.btnWaffle, R.id.btnicecream) to R.drawable.icecream_waffle,
        setOf(R.id.btnWaffle, R.id.btnCake) to R.drawable.cake_waffle,
        setOf(R.id.btnWaffle, R.id.btnCookie) to R.drawable.cookie_waffle,
        setOf(R.id.btnWaffle, R.id.btnEgg) to R.drawable.egg_waffle,
        setOf(R.id.btnWaffle, R.id.btnsyrup) to R.drawable.syrup_waffle,
        setOf(R.id.btnWaffle, R.id.btnWaffle) to R.drawable.double_waffle,
    )
    //DEFAULT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FOR THE MUSIC TO START IMMIDIATELY
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm)
        mediaPlayer.isLooping = true //ALSO LOOPING CUS ITS LIKE 3 MINS OR SMTH
        mediaPlayer.start()

        selected1 = findViewById(R.id.selectedIngredient1)
        selected2 = findViewById(R.id.selectedIngredient2)
        resultImage = findViewById(R.id.resultImage)
        btnAdd = findViewById(R.id.btnAdd)
        btnClear = findViewById(R.id.btnClear)
        btnInstruct = findViewById(R.id.btninstruc)

        //ANIM BOUNCE
        val bounceAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)

        //SETS ANIM FOR THE INGR AND SELECTION
        for (id in ingredientButtonIds) {
            findViewById<ImageButton>(id).setOnClickListener {
                it.startAnimation(bounceAnim)
                handleIngredientSelection(id)
            }
        }

        //CLEARS SELECTIONS
        btnClear.setOnClickListener {
            clearSelection()
        }
        //ADD THEM TOGETHER
        btnAdd.setOnClickListener {
            if (selectedIds.size == 2) {
                showResult()
            } else {
                Toast.makeText(this, "Select your waffle and ingredient", Toast.LENGTH_SHORT).show()
            }
        }

        //INSTRUCTIONS WINDOW POPUP
        btnInstruct.setOnClickListener {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.instructions, null)
            val instructWindow = PopupWindow(popupView, 910, 1900, true)
            instructWindow.showAtLocation(popupView, Gravity.BOTTOM, 18, 500)

            val btnClose: ImageButton = popupView.findViewById(R.id.btnclose)
            btnClose.setOnClickListener {
                instructWindow.dismiss()
            }
        }
    }

    //FOR THE INGR SELECTION AND UPDTAES THE PLATES
    private fun handleIngredientSelection(id: Int) {
        if (selectedIds.size < 2 && !selectedIds.contains(id)) {
            selectedIds.add(id)
            val imgRes = findViewById<ImageButton>(id).drawable
            if (selectedIds.size == 1) {
                selected1.setImageDrawable(imgRes)
            } else {
                selected2.setImageDrawable(imgRes)
            }
        }
    }
    //FOR THE RESULT OF THE MIX INGR ALSO CHECKS IF IT VALID COMB FROM THE LIST ABOVE
    private fun showResult() {
        if (!selectedIds.contains(R.id.btnWaffle)) {
            Toast.makeText(this, "You must first choose your waffle as base!", Toast.LENGTH_SHORT).show()
            resultImage.setImageResource(R.drawable.result_placeholder)
            return
        }

        val comboKey = setOf(selectedIds[0], selectedIds[1])
        val resultRes = combinationMap[comboKey]
        if (resultRes != null) {
            resultImage.setImageResource(resultRes)
        } else {
            resultImage.setImageResource(R.drawable.result_placeholder)
            Toast.makeText(this, "What about your waffles!", Toast.LENGTH_SHORT).show()
        }
    }
    //CLEARS THE SELECTION AND RETURNS THE PLATES
    private fun clearSelection() {
        selectedIds.clear()
        selected1.setImageResource(R.drawable.placeholder)
        selected2.setImageResource(R.drawable.placeholder)
        resultImage.setImageResource(R.drawable.result_placeholder)
    }
}
