package com.example.vkclientcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import com.example.vkclientcompose.ui.theme.ActivityResultTest
import com.example.vkclientcompose.ui.theme.MainScreen
import com.example.vkclientcompose.ui.theme.MyNumber
import com.example.vkclientcompose.ui.theme.SideEffectTest
import com.example.vkclientcompose.ui.theme.VKClientComposeTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKClientComposeTheme {
                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    when (it) {
                        is VKAuthenticationResult.Failed -> {
                            Log.d("LOG_TAG", "VKAuthenticationResult.Failed")
                        }

                        is VKAuthenticationResult.Success -> {
                            Log.d("LOG_TAG", "VKAuthenticationResult.Success")
                        }
                    }
                }
                LaunchedEffect(key1 = Unit) {
                    launcher.launch(listOf(VKScope.WALL))
                }
                MainScreen()
            }
        }
    }
}