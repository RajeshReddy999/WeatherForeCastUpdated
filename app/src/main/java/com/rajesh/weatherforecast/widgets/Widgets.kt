package com.rajesh.weatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rajesh.weatherforecast.R
import com.rajesh.weatherforecast.model.WeatherItem
import com.rajesh.weatherforecast.utils.formatDate
import com.rajesh.weatherforecast.utils.formatDateTime
import com.rajesh.weatherforecast.utils.formatDecimals

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "icon image",
        modifier = Modifier.size(80.dp)
    )
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem, isImperial: Boolean) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.padding(2.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.humidity}%", style = MaterialTheme.typography.titleMedium
            )
        }
        Row(
            modifier = Modifier.padding(2.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Pressure Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.pressure}psi", style = MaterialTheme.typography.titleMedium
            )
        }
        Row(
            modifier = Modifier.padding(2.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${formatDecimals(weather.speed)}" + if (isImperial) "mph" else "m/s",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun SunRiseAndSunSetRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.padding(2.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sun Rise Icon",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formatDateTime(weather.sunrise), style = MaterialTheme.typography.titleMedium
            )
        }
        Row(
            modifier = Modifier.padding(2.dp)
        ) {
            Text(
                text = formatDateTime(weather.sunset), style = MaterialTheme.typography.titleMedium
            )
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sun Set Icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun WeatherDetailedRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp), bottomStart = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            Modifier.padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                formatDate(weather.dt).split(",")[0], modifier = Modifier.padding(start = 5.dp)
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier.padding(0.dp), shape = CircleShape, color = Color(0xFF0EE7BC)
            ) {
                Text(
                    text = weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Magenta.copy(alpha = 0.7f), fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(formatDecimals(weather.temp.max) + "°")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray,
                    )
                ) {
                    append(formatDecimals(weather.temp.min) + "°")
                }
            })
        }
    }
}