package com.example.weathercompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathercompose.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM favorite_tbl")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite_tbl WHERE city =:city")
    suspend fun getFavoriteById(city: String): FavoriteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(city: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(city: FavoriteEntity)

    @Query("DELETE FROM favorite_tbl")
    suspend fun deleteAll()
}