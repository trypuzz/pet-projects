package com.example.ar_faces

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.graphics.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.filament.MaterialInstance
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.ar.core.AugmentedFace
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.ArSceneView
import com.google.ar.sceneform.Sceneform
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.rendering.RenderableInstance
import com.google.ar.sceneform.rendering.Texture
import com.google.ar.sceneform.ux.ArFrontFacingFragment
import com.google.ar.sceneform.ux.AugmentedFaceNode
import java.util.HashSet
import java.util.concurrent.CompletableFuture

class MainActivity : AppCompatActivity() {

    private val loaders: MutableSet<CompletableFuture<*>> = HashSet()
    private var arFragment: ArFrontFacingFragment? = null
    private var arSceneView: ArSceneView? = null
    private var faceTexture: Texture? = null
    private var faceModel: ModelRenderable? = null
    private val facesNodes = HashMap<AugmentedFace, AugmentedFaceNode>()
    private val materialMap: HashMap<Int, MaterialInstance> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        Takt.stock(application)
            .color(Color.RED)
            .seat(Seat.TOP_RIGHT)
            .interval(250)
            .play()

         */
        supportFragmentManager.addFragmentOnAttachListener { fragmentManager: FragmentManager, fragment: Fragment ->
            this.onAttachFragment(
                fragmentManager,
                fragment
            )
        }
        if (savedInstanceState == null) {
            if (Sceneform.isSupported(this)) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.arFragment, ArFrontFacingFragment::class.java, null)
                    .commit()
            }
        }
        loadModels()
        loadTextures()
        loadVariants()
    }

    private fun loadModels() {
        loaders.add(ModelRenderable.builder()
            .setSource(this, Uri.parse("models/yellow_glasses_large.glb"))
            .setIsFilamentGltf(true)
            .build()
            .thenAccept { model: ModelRenderable? -> faceModel = model }
            .exceptionally { throwable: Throwable? ->
                Toast.makeText(this, "Unable to load renderable", Toast.LENGTH_LONG).show()
                null
            })
    }

    private fun loadTextures() {
        loaders.add(Texture.builder()
            .setSource(this, Uri.parse("textures/facialFeatures.png"))
            .setUsage(Texture.Usage.COLOR_MAP)
            .build()
            .thenAccept {
                faceTexture = it
            }
            .exceptionally {
                Toast.makeText(this, "Unable to load texture", Toast.LENGTH_LONG).show()
                null
            });
    }

    fun loadVariants() {
        val chipGroup = findViewById<ChipGroup>(R.id.variant_select)
        chipGroup.setChipSpacing(4)
        val items = resources.getStringArray(R.array.variants).toMutableList()
        items.add("Transparent")
        items.sort()
        items.forEach {
            val chip = Chip(this)
            chip.text = it
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(5, 5, 5, 5)
            chip.layoutParams = layoutParams
            chip.isCheckable = true
            chipGroup.addView(chip)
        }

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chipText = findViewById<Chip?>(checkedId)?.text
            if (chipText != null) {
                if (chipText == "Transparent") {
                    materialMap.forEach { (_, mat) ->
                        mat.setParameter("baseColorFactor", 0f, 0f, 0f, 0.5f)
                    }
                    return@setOnCheckedChangeListener
                }
                val colorInt = chipText.toString().toColorInt()

                materialMap.forEach { (_, mat) ->
                    mat.setParameter(
                        "baseColorFactor",
                        colorInt.red / 255f,
                        colorInt.green / 255f,
                        colorInt.blue / 255f,
                        colorInt.alpha / 255f
                    )
                }
            }
        }
    }

    fun onAttachFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        if (fragment.id == R.id.arFragment) {
            arFragment = fragment as ArFrontFacingFragment
            arFragment?.setOnViewCreatedListener { arSceneView: ArSceneView ->
                onViewCreated(
                    arSceneView
                )
            }
        }
    }

    fun onViewCreated(arSceneView: ArSceneView) {
        this.arSceneView = arSceneView
        arSceneView.setCameraStreamRenderPriority(Renderable.RENDER_PRIORITY_FIRST)

        arFragment?.setOnAugmentedFaceUpdateListener { augmentedFace: AugmentedFace ->
            onAugmentedFaceTrackingUpdate(
                augmentedFace
            )
        }
    }


    fun onAugmentedFaceTrackingUpdate(augmentedFace: AugmentedFace) {
        if (faceModel == null || faceTexture == null) {
            return
        }
        val existingFaceNode = facesNodes[augmentedFace]
        when (augmentedFace.trackingState) {

            TrackingState.TRACKING -> {
                if (existingFaceNode == null) {
                    val faceNode = AugmentedFaceNode(augmentedFace)
                    val modelInstance = faceNode.setFaceRegionsRenderable(faceModel)
                    modelInstance.isShadowCaster = false
                    modelInstance.isShadowReceiver = true
                    arSceneView?.scene?.addChild(faceNode)
                    facesNodes[augmentedFace] = faceNode
                    onNewFaceNodeAdded(faceNode, modelInstance)
                }
            }

            TrackingState.STOPPED -> {
                if (existingFaceNode != null) {
                    arSceneView?.scene?.removeChild(existingFaceNode)
                }
                facesNodes.remove(augmentedFace)
            }

            TrackingState.PAUSED -> {

            }

            null -> {}
        }
    }

    private fun onNewFaceNodeAdded(
        faceNode: AugmentedFaceNode,
        renderableInstance: RenderableInstance
    ) {
        val fa = renderableInstance.filamentAsset
        fa?.let {
            materialMap.clear()
            it.materialInstances.forEachIndexed { index, materialInstance ->
                materialMap[index] = materialInstance
                materialInstance.setParameter("baseColorFactor", 0f, 0f, 0f, 1f)
                Log.d("Material name", ": ${materialInstance.name}")
                val parameters = materialInstance.material.parameters
                parameters.forEach { parameter ->
                    Log.d(
                        "Material Parameter",
                        "name of parameter: ${parameter.name} type: ${parameter.type}"
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        for (loader in loaders) {
            if (!loader.isDone) {
                loader.cancel(true)
            }
        }
    }
}