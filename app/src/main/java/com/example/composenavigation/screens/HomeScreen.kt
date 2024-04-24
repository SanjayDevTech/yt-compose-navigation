package com.example.composenavigation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun HomeScreen(navigateToDetail: () -> Unit) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    Column {
        Text(text = "Home")
        Button(onClick = {
            count++
        }) {
            Text(text = "Count: $count")
        }
        Button(onClick = navigateToDetail) {
            Text(text = "Navigate to Details")
        }
    }
}