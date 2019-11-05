package com.pastel.lilac.clasick_android.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Playlist(
    @Json(name = "cover_path")
    val coverPath: String,
    val id: Int,
    val name: String
)