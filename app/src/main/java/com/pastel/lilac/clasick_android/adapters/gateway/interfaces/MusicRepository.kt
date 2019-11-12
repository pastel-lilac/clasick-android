package com.pastel.lilac.clasick_android.adapters.gateway.interfaces

import com.pastel.lilac.clasick_android.entity.Music
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Gateway Interface
interface MusicRepository {
    // maybe unuse
    @GET("musics/{id}")
    suspend fun getByMusicId(@Path("id") id: String): Music

    @GET("playlist/{id}/musics")
    suspend fun getByPlaylistId(@Path("id") id: String): List<Music>

    companion object Factory {
        fun create(baseURL: String): MusicRepository {
            return  Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                .build()
                .create(MusicRepository::class.java)
        }
    }
}