package com.example.weathercompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathercompose.data.local.dao.WeatherDao
import com.example.weathercompose.model.FavoriteEntity


@Database(
    entities = [FavoriteEntity::class], version = 1
)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}