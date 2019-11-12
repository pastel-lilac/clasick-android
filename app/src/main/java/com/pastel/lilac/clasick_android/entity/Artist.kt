package com.pastel.lilac.clasick_android.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Artist (
    val id: Int,
    val name: String
)