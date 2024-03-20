package com.example.weathercompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weathercompose.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM favorite")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Insert
    suspend fun saveFavorite(city: FavoriteEntity)
}