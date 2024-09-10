package com.example.vkclientcompose.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SideEffectTest(number: MyNumber) {
    Column() {
        LazyColumn {
            repeat(5) {
                item {
                    Text(text = number.a.toString())
                }
            }
        }
        number.a = 5
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            repeat(5) {
                item {
                    Text(text = number.a.toString())
                }
            }
        }
    }
}

data class MyNumber(var a: Int) {

}