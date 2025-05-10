package com.millie.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.millie.android.data.model.CatImageEntity

@Database(entities = [CatImageEntity::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}