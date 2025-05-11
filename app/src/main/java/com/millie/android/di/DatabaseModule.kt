package com.millie.android.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
        )
            .addMigrations(MIGRATION_2_3)
            .build()
    }

    @Provides
    fun provideCatDao(database: CatDatabase): CatDao = database.catDao()
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            ALTER TABLE cat_images
            ADD COLUMN localImagePath TEXT
        """.trimIndent()
        )
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // DROP COLUMN을 미지원

        // 1. 기존 테이블 제거
        db.execSQL("DROP TABLE cat_images")

        // 2. localImagePath 컬럼 제거후 재생성
        db.execSQL(
            """
            CREATE TABLE cat_images (
                id TEXT PRIMARY KEY NOT NULL,
                url TEXT NOT NULL,
                width INTEGER NOT NULL,
                height INTEGER NOT NULL
            )
        """.trimIndent()
        )
    }
}
