package com.example.qr_generator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.*
import me.dm7.barcodescanner.zbar.ZBarScannerView
import java.util.regex.Matcher
import java.util.regex.Pattern

class ScanQR : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private lateinit var codeScanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)

        codeScanner = CodeScanner(this, scannerView)

        codeScanner.camera = CodeScanner.CAMERA_BACK //back or front camera
        codeScanner.formats = CodeScanner.ALL_FORMATS

        codeScanner.autoFocusMode = AutoFocusMode.SAFE // autofocus also can be CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // can be CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        //result of program
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
                var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                var clip = ClipData.newPlainText("label", it.text)
                clipboard.setPrimaryClip(clip)
                val isContain = containsURL(it.text)
                if (isContain){
                    val openPage = Intent(Intent.ACTION_VIEW, Uri.parse(it.text))
                    startActivity(openPage)
                }
            }
            finish()
        }
        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }
    private fun containsURL(content: String): Boolean {
        val REGEX = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
        val p: Pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE)
        val m: Matcher = p.matcher(content)
        return m.find()
    }
    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }
    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    override fun handleResult(p0: me.dm7.barcodescanner.zbar.Result?) {
        TODO("Not yet implemented")
    }
}