package com.xuancanhit.mireaweatherapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xuancanhit.mireaweatherapp.ui.theme.BackgroundDark
import com.xuancanhit.mireaweatherapp.ui.theme.DarkBlue

@Composable
fun CurrentWeatherDetailRow(title1: String, value1: String, title2: String, value2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CurrentWeatherDetailCard(title = title1, value = value1)
        CurrentWeatherDetailCard(title = title2, value = value2)
    }
}

@Composable
private fun CurrentWeatherDetailCard(title: String, value: String) {
    Card(
        modifier = Modifier.size(172.dp),
        colors = CardDefaults.cardColors(containerColor = DarkBlue),
        shape = RoundedCornerShape(15.dp),
        border = null
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 8.dp), Alignment.TopStart) {
            Text(text = title, style = MaterialTheme.typography.displaySmall.copy(fontSize = 18.sp))
        }
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            Text(
                modifier = Modifier.padding(bottom = 25.dp),
                text = value,
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 36.sp)
            )
        }
    }
}