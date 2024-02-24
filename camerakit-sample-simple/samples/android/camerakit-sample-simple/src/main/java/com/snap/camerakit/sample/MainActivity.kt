package com.snap.camerakit.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.snap.camerakit.support.app.CameraActivity

private const val TAG = "MainActivity"
private val LENS_GROUP_IDS = arrayOf(BuildConfig.LENS_GROUP_ID_TEST)
private const val APPLY_LENS_BY_ID = "6ef1f6be-187c-4993-858a-3a70fdd71f49"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

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
                }

                is CameraActivity.Capture.Result.Success.Image -> {
                    imageView.visibility = View.VISIBLE
                    imageView.setImageURI(result.uri)
                    videoView.visibility = View.GONE
                    captureResultLabel.text = null
                }

                is CameraActivity.Capture.Result.Cancelled -> {
                    captureResultLabel.text = getString(R.string.label_result_none)
                    clearMediaPreviews()
                }

                is CameraActivity.Capture.Result.Failure -> {
                    captureResultLabel.text = getString(
                        R.string.label_result_failure, result.exception.toString()
                    )
                    clearMediaPreviews()
                }
            }
        }

        val key = intent.getStringExtra("etNumber")

        findViewById<Button>(R.id.button_capture_lens).setOnClickListener {
            captureLauncher.launch(
                CameraActivity.Configuration.WithLens(
                    lensGroupId = LENS_GROUP_IDS.first(),
                    lensId = (when (key) {
                        "1"-> "96146402-39c3-4080-834b-343458977cb9"
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
    }
}
