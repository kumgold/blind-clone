package com.example.blindclone.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdBox() {
    Box(
        modifier = Modifier.height(80.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
    )
}