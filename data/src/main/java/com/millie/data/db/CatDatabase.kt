package com.millie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.millie.data.model.CatImageEntity

@Database(entities = [CatImageEntity::class], version = 3)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}