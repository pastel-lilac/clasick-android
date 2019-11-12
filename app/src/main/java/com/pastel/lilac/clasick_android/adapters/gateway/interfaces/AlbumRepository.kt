package com.pastel.lilac.clasick_android.adapters.gateway.interfaces

import com.pastel.lilac.clasick_android.entity.Artist
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumRepository {
    // maybe unuse
    @GET("artist/{id}/albums")
    suspend fun getByArtistId(@Path("id") id: String): List<Artist>

    companion object Factory {
        fun create(baseURL: String): AlbumRepository {
            return  Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                .build()
                .create(AlbumRepository::class.java)
        }
    }
}