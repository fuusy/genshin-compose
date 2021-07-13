package com.example.genshincompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import com.example.genshincompose.ui.theme.GenshinComposeTheme
import com.example.genshincompose.ui.page.Main


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenshinComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.White) {
                    Main()
                }
            }
        }

    }

}