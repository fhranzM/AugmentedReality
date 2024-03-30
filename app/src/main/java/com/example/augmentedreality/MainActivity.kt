package com.example.augmentedreality

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.isGone
import com.google.android.filament.Engine
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode


class MainActivity : ComponentActivity() {

    lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var modelNode: ArModelNode


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentView(R.layout.activity_main)

            sceneView = findViewById(R.id.sceneView)

            placeButton = findViewById(R.id.place)

            placeButton.setOnClickListener {
                placeModel()
            }

            modelNode = ArModelNode(engine = Engine.create()).apply {
                loadModelGlbAsync(
                    glbFileLocation = "models/simple_shirt_without_textures.glb"
                )

                {
                    sceneView.planeRenderer.isVisible = true
                }

                onAnchorChanged = {
                    placeButton.isGone
                }

            }

            sceneView.addChild(modelNode)


        }

    }

    private fun placeModel () {
        modelNode?.anchor()
        sceneView.planeRenderer.isVisible = false
    }

}
