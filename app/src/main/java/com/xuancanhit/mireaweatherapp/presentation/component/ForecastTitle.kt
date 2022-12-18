package com.xuancanhit.mireaweatherapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForecastTitle(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(start = 32.dp, top = 8.dp),
        text = text,
        style = MaterialTheme.typography.displayMedium.copy(fontSize = 18.sp)
    )
}