package com.pastel.lilac.clasick_android.adapters.gateway.interfaces

import com.pastel.lilac.clasick_android.entity.Artist
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistRepository {
    // maybe unuse
    @GET("artists/{id}")
    suspend fun getByArtistId(@Path("id") id: String)

    @GET("artists")
    suspend fun get(@Query("limits") limitNum: Int): List<Artist>

    companion object Factory {
        fun create(baseURL: String): ArtistRepository {
            return  Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                .build()
                .create(ArtistRepository::class.java)
        }
    }
}