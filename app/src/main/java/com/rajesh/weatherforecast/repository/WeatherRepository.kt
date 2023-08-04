package com.rajesh.weatherforecast.repository

import android.util.Log
import com.rajesh.weatherforecast.data.DataOrException
import com.rajesh.weatherforecast.model.Weather
import com.rajesh.weatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery: String, units: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery,units = units)
        } catch (e: Exception) {
            Log.d("Weatther Exception", "getWeather:$e ")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }
}