package com.ugdgomezdiez.androidtraining.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.features.ex01.presentation.Ex01MainActivity
import com.ugdgomezdiez.androidtraining.features.ex02.presentation.Ex02MainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }
    private fun setupView(){
        findViewById<Button>(R.id.action_ex01).setOnClickListener{
            startActivity(Intent(this, Ex01MainActivity::class.java))
        }
        findViewById<Button>(R.id.action_ex02).setOnClickListener{
            startActivity(Intent(this, Ex02MainActivity::class.java))
        }


    }

}