package com.example.qr_generator

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var im: ImageView? = null
    var bGenerate: Button? = null
    var bScanQR: Button? = null
    var editText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bGenerate = findViewById(R.id.button)
        im = findViewById(R.id.imageView)
        bScanQR = findViewById(R.id.bScan)
        editText = findViewById(R.id.editText)
        bGenerate?.isEnabled = false

        bGenerate?.setOnClickListener {
            generateQRCode(editText?.text.toString())
        }
        bScanQR?.setOnClickListener {
            startActivity(Intent(this, ScanQR::class.java))
        }
    }

    private fun generateQRCode(text: String) {
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 700)
        try {
            val bMap = qrGenerator.bitmap
            im?.setImageBitmap(bMap)
        } catch (_: java.lang.Exception) {
        }
    }

    override fun onResume() {
        editText?.doAfterTextChanged {
            bGenerate?.isEnabled = editText?.text.toString() != ""
        }
        super.onResume()
    }
}