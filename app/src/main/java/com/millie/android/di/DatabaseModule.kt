package com.millie.android.di

import android.content.Context
import androidx.room.Room
import com.millie.android.data.db.CatDao
import com.millie.android.data.db.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CatDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = CatDatabase::class.java,
            name = "cat_db"
        ).build()
    }

    @Provides
    fun provideCatDao(database: CatDatabase): CatDao = database.catDao()
}