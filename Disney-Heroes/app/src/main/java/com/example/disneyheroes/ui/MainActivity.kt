package com.example.disneyheroes.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.disneyheroes.R
import com.example.disneyheroes.databinding.ActivityMainBinding
import com.example.disneyheroes.utils.navigationFragments
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_open_start_app_fragment)
        binding.root.startAnimation(anim)

        navigationFragments(supportFragmentManager, StartAppFragment())
    }
}