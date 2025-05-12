package com.millie.android.data.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageCacheManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val imageDir = File(context.filesDir, "images").apply { mkdirs() }

    fun getFilePath(id: String): String = File(imageDir, "$id.jpg").absolutePath

    suspend fun saveFile(url: String, id: String): Boolean = withContext(Dispatchers.IO) {
        val file = File(getFilePath(id))
        if (file.exists()) return@withContext true

        return@withContext try {
            val bitmap = Glide.with(context).asBitmap().load(url).submit().get()
            FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.JPEG, 80, it) }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }
}