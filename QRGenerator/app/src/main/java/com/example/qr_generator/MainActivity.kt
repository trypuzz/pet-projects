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
            generateQRCode("https://ru.stackoverflow.com/questions/965315/%D0%9A%D0%B0%D0%BA-%D0%BF%D1%80%D0%B8-%D0%BE%D1%82%D0%BA%D1%80%D1%8B%D1%82%D0%B8%D0%B8-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F-%D0%BE%D1%82%D0%BA%D1%80%D1%8B%D0%B2%D0%B0%D1%82%D1%8C-%D1%81%D1%81%D1%8B%D0%BB%D0%BA%D1%83-%D0%B2-%D0%B1%D1%80%D0%B0%D1%83%D0%B7%D0%B5%D1%80%D0%B5")
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