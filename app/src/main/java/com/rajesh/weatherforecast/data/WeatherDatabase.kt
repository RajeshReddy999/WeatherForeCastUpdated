package com.rajesh.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rajesh.weatherforecast.model.Favorite
import com.rajesh.weatherforecast.model.Unit

@Database(entities = [Favorite::class,Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}