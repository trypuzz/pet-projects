package com.example.simple_authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simple_authorization.databinding.ActivityMainBinding

class SignInUpActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val message = intent.getStringExtra("key")
        bindingClass
    }
}