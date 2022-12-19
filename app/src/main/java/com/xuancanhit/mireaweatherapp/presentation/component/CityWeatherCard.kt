package com.xuancanhit.mireaweatherapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xuancanhit.mireaweatherapp.ui.theme.DarkBlue
import com.xuancanhit.mireaweatherapp.ui.theme.White

@Composable
fun CityWeatherCard(
    modifier: Modifier = Modifier,
    degree: String,
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
    weatherImage: Int,
    onClick: () -> Unit = {},
    isItDb: Boolean = false
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = DarkBlue),
        shape = MaterialTheme.shapes.medium
    ) {
        WeatherImage(degree, latitude, longitude, city, country, description, onClick, isItDb,weatherImage = weatherImage)
    }
}

@Composable
private fun WeatherImage(
    degree: String,
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
    onClick: () -> Unit = {},
    isItDb: Boolean = false,
    weatherImage: Int
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.CenterEnd
    ) {
        Image(
            modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 2),
            painter = painterResource(id = weatherImage),
            contentDescription = null,
        )
        WeatherInfo(degree, latitude, longitude, city, country, description, onClick, isItDb)
    }
}

@Composable
private fun WeatherInfo(
    degree: String,
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
    onClick: () -> Unit = {},
    isItDb: Boolean = false
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DegreeAndButtonSection(degree = degree, isItDb = isItDb, onClick = onClick)
        LocationAndDescription(latitude, longitude, city, country, description)
    }
}

@Composable
private fun DegreeAndButtonSection(degree: String, isItDb: Boolean, onClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(modifier = Modifier.padding(start = 16.dp) ,text = degree, fontSize = 76.sp)
        IconButtonSection(isItDb, onClick)
    }
}

@Composable
private fun LocationAndDescription(
    latitude: Double,
    longitude: Double,
    city: String,
    country: String,
    description: String,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, top = 40.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(start = 16.dp), horizontalAlignment = Alignment.Start) {
            Text(text = "H:${latitude}  L:${longitude}")
            Text(text = "${city}, $country")

        }
        Text(modifier = Modifier.padding(end = 16.dp), text = description)
    }
}


@Composable
private fun IconButtonSection(isItDb: Boolean, onClick: () -> Unit) {
    if (isItDb) {
        ActionButton(onClick, Icons.Filled.Close)
    } else {
        ActionButton(onClick, Icons.Filled.Add)
    }
}

@Composable
private fun ActionButton(onClick: () -> Unit, imageVector: ImageVector) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector,
            contentDescription = null,
            tint = White
        )
    }
}