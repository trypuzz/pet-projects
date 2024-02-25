package com.snap.camerakit.sample

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.snap.camerakit.support.app.CameraActivity
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream

import java.net.URL


private const val TAG = "MainActivity"
private val LENS_GROUP_IDS = arrayOf(BuildConfig.LENS_GROUP_ID_TEST)
private var PATHtoDOWNLOAD =
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        .toString() + File.separator + R.string.folder_name + File.separator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val key = intent.getStringExtra("etNumber")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val downloadButton = findViewById<Button>(R.id.button_capture_download)
        val captureResultLabel = findViewById<TextView>(R.id.label_capture_result)
        val imageView = findViewById<ImageView>(R.id.image_preview)
        val videoView = findViewById<VideoView>(R.id.video_preview).apply {
            setOnPreparedListener { mediaPlayer ->
                mediaPlayer.isLooping = true
            }
        }
        val clearMediaPreviews = {
            videoView.visibility = View.GONE
            imageView.visibility = View.GONE
        }


        val captureLauncher = (this as ComponentActivity).registerForActivityResult(CameraActivity.Capture) { result ->
            Log.d(TAG, "Got capture result: $result")
            when (result) {
                is CameraActivity.Capture.Result.Success.Video -> {
                    videoView.visibility = View.VISIBLE
                    videoView.setVideoURI(result.uri)
                    videoView.start()
                    imageView.visibility = View.GONE
                    captureResultLabel.text = null
                    downloadButton.setOnClickListener {
                        createDirectoryIfNotExists()
                        downloadImage(
                            result.uri.toString(),
                            PATHtoDOWNLOAD + "video$key(${countOfFilesExistsInDirectory("video$key")}).mp4"
                        )
                    }
                }

                is CameraActivity.Capture.Result.Success.Image -> {
                    imageView.visibility = View.VISIBLE
                    imageView.setImageURI(result.uri)
                    videoView.visibility = View.GONE
                    captureResultLabel.text = null
                    downloadButton.setOnClickListener {
                        createDirectoryIfNotExists()
                        downloadImage(
                            result.uri.toString(),
                            PATHtoDOWNLOAD + "image$key(${countOfFilesExistsInDirectory("image$key")}).jpg"
                        )
                    }
                }

                is CameraActivity.Capture.Result.Cancelled -> {
                    captureResultLabel.text = getString(R.string.label_result_none)
                    clearMediaPreviews()
                    downloadButton.visibility = View.GONE
                }

                is CameraActivity.Capture.Result.Failure -> {
                    captureResultLabel.text = getString(
                        R.string.label_result_failure, result.exception.toString()
                    )
                    clearMediaPreviews()
                    downloadButton.visibility = View.GONE
                }
            }
        }

        openCameraView(captureLauncher, key)
        findViewById<Button>(R.id.button_capture_lens).setOnClickListener {
            openCameraView(captureLauncher, key)
        }
    }

    private fun openCameraView(captureLauncher: ActivityResultLauncher<CameraActivity.Configuration>, key: String?) {
        captureLauncher.launch(
            CameraActivity.Configuration.WithLens(
                lensGroupId = LENS_GROUP_IDS.first(),
                lensId = (when (key) {
                    "1" -> "96146402-39c3-4080-834b-343458977cb9"
                    "2" -> "6ef1f6be-187c-4993-858a-3a70fdd71f49"
                    "3" -> "0751b9cb-c0cd-42a4-8aac-7ae3b946ea3d"
                    else -> "96146402-39c3-4080-834b-343458977cb9" //dress
                    //"6ef1f6be-187c-4993-858a-3a70fdd71f49" //bomber
                    //"0751b9cb-c0cd-42a4-8aac-7ae3b946ea3d" //coat
                })
                //APPLY_LENS_BY_ID
            )
        )
    }

    private fun downloadImage(uri: String, destinationPath: String) {
        try {
            val url = URL(uri)
            val connection = url.openConnection()
            connection.connect()

            val inputStream = BufferedInputStream(url.openStream())
            val outputStream = FileOutputStream(destinationPath)

            val data = ByteArray(1024)
            var bytesRead = inputStream.read(data)
            while (bytesRead != -1) {
                outputStream.write(data, 0, bytesRead)
                bytesRead = inputStream.read(data)
            }

            outputStream.close()
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun countOfFilesExistsInDirectory(fileName: String): Int {
        val directory = File(PATHtoDOWNLOAD)
        File(directory, fileName)
        val files = directory.listFiles()
        var count = 0

        if (files != null) {
            for (file in files) {
                if (file.isFile && file.name.contains(fileName)) {
                    count++
                }
            }
        }
        return count
    }

    private fun createDirectoryIfNotExists() {
        val directory = File(PATHtoDOWNLOAD)
        if (!directory.exists()) {
            directory.mkdirs()
        }
    }
}
