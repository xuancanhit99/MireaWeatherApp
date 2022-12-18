package com.xuancanhit.mireaweatherapp.presentation.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.xuancanhit.mireaweatherapp.core.helpers.EpochConverter
import com.xuancanhit.mireaweatherapp.core.helpers.SetError
import com.xuancanhit.mireaweatherapp.core.utils.AppStrings
import com.xuancanhit.mireaweatherapp.core.utils.ErrorCardConsts
import com.xuancanhit.mireaweatherapp.core.utils.ExceptionTitles
import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity
import com.xuancanhit.mireaweatherapp.presentation.component.*


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    padding: PaddingValues,
    setFabOnClick: (() -> Unit) -> Unit,
    onNavigateToSearchCityScreen: () -> Unit
) {
//    LaunchedEffect(Unit) {
//        setFabOnClick { viewModel.fetchWeather() }
//    }

    val homeCurrentWeatherState by viewModel.homeForecastState.collectAsState()
    val activity = (LocalContext.current as? Activity)

    Surface(
        color = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
        modifier = Modifier
            .padding(padding),
    ) {
        WeatherSection(homeCurrentWeatherState) { activity?.finish() }
    }

}


@Composable
private fun WeatherSection(currentWeatherState: HomeForecastState, errorCardOnClick: () -> Unit) {
    when (currentWeatherState) {
        is HomeForecastState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressBar(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
            }
        }
        is HomeForecastState.Success -> {
            if (currentWeatherState.forecast != null) {
                //CurrentWeatherSection(currentWeatherState.forecast)
                DetailsSection(currentWeatherState.forecast)
            }
        }
        is HomeForecastState.Error -> {
            ErrorCard(
                modifier = Modifier.fillMaxSize(),
                errorTitle = currentWeatherState.errorMessage ?: ExceptionTitles.UNKNOWN_ERROR,
                errorDescription = SetError.setErrorCard(
                    currentWeatherState.errorMessage ?: ExceptionTitles.UNKNOWN_ERROR
                ),
                errorButtonText = ErrorCardConsts.BUTTON_TEXT,
                errorCardOnClick,
                cardModifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp / 4 + 48.dp)
                    .padding(horizontal = 64.dp)
            )
        }
    }
}

@Composable
private fun capitalizeDes(todayWeather: ForecastCity): String {
    val line = todayWeather.weatherList[0].weatherStatus[0].description
    val arr: List<String> = line.split(" ")
    val sb = StringBuffer()
    for (i in arr.indices) {
        sb.append(arr[i][0].uppercaseChar())
            .append(arr[i].substring(1)).append(" ")
    }
    return sb.toString().trim { it <= ' ' }
    //return line[0].uppercaseChar().toString() + line.substring(1)
}

@Composable
private fun CurrentWeatherSection(todayWeather: ForecastCity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = todayWeather.cityDtoData.cityName,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "${todayWeather.weatherList[0].weatherData.temp.toInt()}${AppStrings.degree}",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = capitalizeDes(todayWeather),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = "H:${todayWeather.cityDtoData.coordinate.longitude}°  L:${todayWeather.cityDtoData.coordinate.latitude}°",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
private fun DetailsSection(forecast: ForecastCity) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                CurrentWeatherSection(forecast)
                ForecastSection(forecast)
                WeatherDetailSection(forecast)
            }
        }
    }
}

@Composable
private fun ForecastSection(forecastData: ForecastCity) {
    ForecastTitle(text = AppStrings.hourly_forecast)
    ForecastLazyRow(forecasts = forecastData.weatherList.take(8))
    ForecastTitle(text = AppStrings.daily_forecast)
    ForecastLazyRow(forecasts = forecastData.weatherList.takeLast(32))
}

@Composable
private fun WeatherDetailSection(currentWeather: ForecastCity) {
    CurrentWeatherDetailRow(
        title1 = AppStrings.temp,
        value1 = "${currentWeather.weatherList[0].weatherData.temp}${AppStrings.degree}",
        title2 = AppStrings.feels_like,
        value2 = "${currentWeather.weatherList[0].weatherData.feelsLike}${AppStrings.degree}"
    )
    CurrentWeatherDetailRow(
        title1 = AppStrings.cloudiness,
        value1 = "${currentWeather.weatherList[0].cloudiness.cloudiness}%",
        title2 = AppStrings.humidity,
        value2 = "${currentWeather.weatherList[0].weatherData.humidity}%"
    )
    CurrentWeatherDetailRow(
        title1 = AppStrings.sunrise,
        value1 = "${EpochConverter.readTimestamp(currentWeather.cityDtoData.sunrise)}AM",
        title2 = AppStrings.sunset,
        value2 = "${EpochConverter.readTimestamp(currentWeather.cityDtoData.sunset)}PM"
    )
    CurrentWeatherDetailRow(
        title1 = AppStrings.wind,
        value1 = "${currentWeather.weatherList[0].wind.speed}${AppStrings.metric}",
        title2 = AppStrings.pressure,
        value2 = "${currentWeather.weatherList[0].weatherData.pressure}"
    )
}