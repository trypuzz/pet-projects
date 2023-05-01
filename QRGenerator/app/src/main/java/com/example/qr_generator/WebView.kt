package com.example.qr_generator

import android.os.Build
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.qr_generator.databinding.ActivityWebViewBinding


class WebView : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        val view = binding.root
        val link = intent.getStringExtra("LINK")
        webViewSetup(link.toString())
        setContentView(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun webViewSetup(link: String) {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.apply {
            loadUrl(link)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }
}