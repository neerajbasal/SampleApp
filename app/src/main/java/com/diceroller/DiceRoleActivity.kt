package com.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.util.*

class DiceRoleActivity : AppCompatActivity() {
    private lateinit var ivDice: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_role)
        val rollButton: Button = findViewById(R.id.btn_roll)
        ivDice = findViewById(R.id.iv_DiceImage)
        rollButton.text = "Hello Kotlin"
        rollButton.setOnClickListener() {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            roleDice()
        }

    }

    private fun roleDice() {
        val value = Random().nextInt(6) + 1
        val drawableRes = when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> {
                R.drawable.empty_dice
            }

        }
        ivDice.setImageResource(drawableRes)
    }
}
