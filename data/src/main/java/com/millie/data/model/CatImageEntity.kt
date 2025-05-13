package com.millie.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_images")
data class CatImageEntity(
    @PrimaryKey val id: String,
    val url: String,
    val width: Int,
    val height: Int,
)