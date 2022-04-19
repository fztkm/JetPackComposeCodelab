package com.fztkm.layoutscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import com.fztkm.layoutscodelab.ui.theme.LayoutsCodelabTheme

class CustomLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BodyContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun BodyContent() {
    LayoutsCodelabTheme {
        MyOwnColumn {
            Text("MyOwnColumn")
            Text("LayoutでColumnやRowを作れる")
            Text("子要素を計測した値を用いて相対位置に配置する")
        }
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    //custom layout attributes
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        //List of measured children
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        var yPosition = 0
        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            //Place children
            placeables.forEach { placeable ->
                //Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }
    }
}

