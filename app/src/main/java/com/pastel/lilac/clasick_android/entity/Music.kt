package com.pastel.lilac.clasick_android.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Music(
    @Json(name = "artist_id")
    val artistId: Int,
    val description: String,
    val id: Int,
    val name: String,
    @Json(name = "source_path")
    val sourcePath: String
)