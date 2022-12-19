package com.xuancanhit.mireaweatherapp.presentation.screens.manage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.xuancanhit.mireaweatherapp.core.utils.AppStrings
import com.xuancanhit.mireaweatherapp.core.utils.Constants
import com.xuancanhit.mireaweatherapp.core.utils.ErrorCardConsts
import com.xuancanhit.mireaweatherapp.domain.model.MyCity
import com.xuancanhit.mireaweatherapp.presentation.component.ErrorCard
import com.xuancanhit.mireaweatherapp.R
import com.xuancanhit.mireaweatherapp.core.helpers.HourConverter
import com.xuancanhit.mireaweatherapp.core.utils.WeatherType
import com.xuancanhit.mireaweatherapp.domain.model.ForecastCity
import com.xuancanhit.mireaweatherapp.presentation.component.CircularProgressBar
import com.xuancanhit.mireaweatherapp.presentation.component.CityWeatherCard


@Composable
fun ManageScreen(viewModel: ManageViewModel, onNavigateToHomeScreen: () -> Unit) {
    val manageState by viewModel.manageState.collectAsState()
    val myCitiesState by viewModel.myCitiesState.collectAsState()



    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(top = 50.dp)

    ) {


        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            ManageScreenContent(
                viewModel = viewModel,
                manageState = manageState,
                myCitiesState = myCitiesState
            )
        }




    }
}

@Composable
private fun ManageScreenContent(
    viewModel: ManageViewModel,
    manageState: ManageState,
    myCitiesState: MyCitiesState
) {
    SearchField(viewModel)
    if (viewModel.isCitySearched) {
        when (manageState) {
            is ManageState.Loading -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressBar(
                        modifier = Modifier
                            .size(LocalConfiguration.current.screenWidthDp.dp / 3)
                            .padding(top = 16.dp),
                    )
                }
            }
            is ManageState.Success -> {
                if (manageState.forecast != null) {
                    WantedCityWeatherSection(manageState.forecast, viewModel)
                }
            }
            is ManageState.Error -> {
                SearchResultErrorMessage(manageState.errorMessage, viewModel)
            }
        }
        MyCities(myCitiesState, viewModel)
    } else {
        MyCities(myCitiesState, viewModel)
    }
}


@Composable
private fun SearchField(viewModel: ManageViewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = viewModel.searchFieldValue,
        onValueChange = { viewModel.updateSearchField(it) },
        label = {
            Text(text = AppStrings.placeholder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { viewModel.manageClick() }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun WantedCityWeatherSection(forecast: ForecastCity, viewModel: ManageViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(text = AppStrings.subtitle2, style = MaterialTheme.typography.displayMedium)
        CityWeatherCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 4)
                .padding(top = 16.dp),
            degree = "${forecast.weatherList[0].weatherData.temp.toInt()}${AppStrings.degree}",
            latitude = forecast.cityDtoData.coordinate.latitude,
            longitude = forecast.cityDtoData.coordinate.longitude,
            city = forecast.cityDtoData.cityName,
            country = forecast.cityDtoData.country,
            description = forecast.weatherList[0].weatherStatus[0].description,
            weatherImage = WeatherType.setWeatherType(
                forecast.weatherList[0].weatherStatus[0].mainDescription,
                forecast.weatherList[0].weatherStatus[0].description,
                HourConverter.convertHour(forecast.weatherList[0].date.substring(11, 13)),
            ),
            isItDb = false,
            onClick = {
                viewModel.addMyCity(
                    MyCity(
                        temp = forecast.weatherList[0].weatherData.temp,
                        latitude = forecast.cityDtoData.coordinate.latitude,
                        longitude = forecast.cityDtoData.coordinate.longitude,
                        cityName = forecast.cityDtoData.cityName,
                        country = forecast.cityDtoData.country,
                        description = forecast.weatherList[0].weatherStatus[0].description,
                        weatherImage = WeatherType.setWeatherType(
                            forecast.weatherList[0].weatherStatus[0].mainDescription,
                            forecast.weatherList[0].weatherStatus[0].description,
                            HourConverter.convertHour(forecast.weatherList[0].date.substring(11, 13)),
                        ),
                    )
                )
            }
        )
    }
}

@Composable
private fun MyCities(myCitiesState: MyCitiesState, viewModel: ManageViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.Start
    ) {
        when (myCitiesState) {
            is MyCitiesState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressBar(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
                }
            }
            is MyCitiesState.Success -> {
                if (myCitiesState.forecast.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        EmptyCityListMessage()
                    }
                } else {
                    CityListSection(myCitiesState.forecast, viewModel)
                }
            }
            is MyCitiesState.Error -> {
                CityListErrorMessage(myCitiesState.errorMessage)
            }
        }
    }
}

@Composable
private fun SearchResultErrorMessage(errorMessage: String?, viewModel: ManageViewModel) {
    ErrorCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        errorTitle = AppStrings.error_title,
        errorDescription = errorMessage ?: Constants.UNKNOWN_ERROR,
        errorButtonText = ErrorCardConsts.BUTTON_TEXT,
        onClick = { viewModel.errorOnClick() },
        cardModifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp / 4 + 48.dp)
    )
}

@Composable
private fun EmptyCityListMessage() {
    Image(
        modifier = Modifier
            .size(128.dp)
            .padding(bottom = 16.dp),
        painter = painterResource(id = R.drawable.no_city),
        contentDescription = null
    )
    Text(text = AppStrings.no_city)
}

@Composable
private fun CityListSection(cityList: List<MyCity>, viewModel: ManageViewModel) {
    Text(
        text = AppStrings.subtitle1,
        style = MaterialTheme.typography.displayMedium
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(cityList) {
            CityWeatherCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp / 4)
                    .padding(top = 16.dp),
                degree = "${it.temp.toInt()}${AppStrings.degree}",
                latitude = it.latitude,
                longitude = it.longitude,
                city = it.cityName,
                country = it.country,
                description = it.description,
                weatherImage = it.weatherImage,
                isItDb = true,
                onClick = { viewModel.removeMyCity(it.cityName) }
            )
        }
    }
}

@Composable
private fun CityListErrorMessage(errorMessage: String?) {
    ErrorCard(
        errorTitle = errorMessage ?: Constants.UNKNOWN_ERROR,
        errorDescription = errorMessage ?: Constants.UNKNOWN_ERROR,
        errorButtonText = ErrorCardConsts.BUTTON_TEXT,
        onClick = {},
        cardModifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp / 4 + 48.dp)
    )
}