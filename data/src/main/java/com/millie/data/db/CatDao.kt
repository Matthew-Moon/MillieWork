package com.millie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.millie.data.model.CatImageEntity

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<CatImageEntity>)

    @Query("SELECT * FROM cat_images ORDER BY RANDOM()")
    suspend fun getAll(): List<CatImageEntity>
}