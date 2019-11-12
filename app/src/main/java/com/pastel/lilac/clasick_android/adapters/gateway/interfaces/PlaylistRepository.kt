package com.pastel.lilac.clasick_android.adapters.gateway.interfaces

import com.pastel.lilac.clasick_android.entity.Playlist
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Gateway Interface
interface PlaylistRepository {
    @GET("playlists")
    suspend fun get(userIdentifier: String): List<Playlist>

    companion object Factory {
        fun create(baseURL: String): PlaylistRepository {
            return  Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                .build()
                .create(PlaylistRepository::class.java)
        }
    }
}