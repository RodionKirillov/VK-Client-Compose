package com.example.vkclientcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vkclientcompose.ui.theme.MainScreen
import com.example.vkclientcompose.ui.theme.VKClientComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKClientComposeTheme {
                MainScreen()
            }
        }
    }
}