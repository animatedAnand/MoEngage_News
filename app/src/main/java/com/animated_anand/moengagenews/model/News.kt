package com.animated_anand.moengagenews.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

data class News(
    val sourceId: String,
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    var isBookmarked: Boolean = false // Default to false
)

