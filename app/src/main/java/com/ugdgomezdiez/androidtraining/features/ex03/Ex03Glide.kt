package com.ugdgomezdiez.androidtraining.features.ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ugdgomezdiez.androidtraining.R
import com.ugdgomezdiez.androidtraining.app.extensions.setUrl
import com.ugdgomezdiez.androidtraining.databinding.ActivityEx03GlideBinding

class Ex03Glide : AppCompatActivity() {

    private lateinit var binding: ActivityEx03GlideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex03_glide)
    }

    private fun setupBinding(){
        binding = ActivityEx03GlideBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView(){
        binding.let {
            it.imageUrl.setUrl("https://goo.gl/gEgYUd")
        }
    }
}