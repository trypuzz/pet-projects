package com.example.qr_generator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    var im: ImageView? = null
    var bGenerate: Button? = null
    var bScanQR: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bGenerate = findViewById(R.id.button)
        im = findViewById(R.id.imageView)
        bScanQR = findViewById(R.id.bScan)
        bGenerate?.setOnClickListener {
            generateQRCode("TEST TEXT")
        }
        bScanQR?.setOnClickListener{
            startActivity(Intent(this, ScanQR::class.java))
        }
    }

    private fun generateQRCode(text: String) {
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 700)
        try {
            val bMap = qrGenerator.bitmap
            im?.setImageBitmap(bMap)
        } catch (e: java.lang.Exception) {

        }
    }
}