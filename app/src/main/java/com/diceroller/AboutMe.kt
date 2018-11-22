package com.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.diceroller.R.id.et_nickname
import com.diceroller.databinding.ActivityAboutmeBinding

class AboutMe : AppCompatActivity() {
    //    private lateinit var etNickName: EditText
//    private lateinit var btnDone: Button
    // The layout for this activity is a Data Binding layout so it needs to be inflated using
    // DataBindingUtil.
    private lateinit var binding: ActivityAboutmeBinding
    private val myName: MyName = MyName("Neeraj")
    private val checkStatus:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_aboutme)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_aboutme
        )
//        etNickName = findViewById(et_nickname)
//        btnDone = findViewById(R.id.btn_done)

        binding.btnDone.setOnClickListener() {
            binding.etNickname.setBackgroundColor(getResources().getColor(R.color.accent_material_dark))
            binding.myName = myName
        }


    }
}
