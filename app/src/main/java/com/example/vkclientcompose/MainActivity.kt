package com.example.vkclientcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.vkclientcompose.ui.theme.MainScreen
import com.example.vkclientcompose.ui.theme.VKClientComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKClientComposeTheme {
                MainScreen(viewModel)
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.background)
//                        .statusBarsPadding()
//                        .navigationBarsPadding()
//                        .padding(8.dp)
//                ) {
//                    PostCard()
//                }
            }
        }
    }
}