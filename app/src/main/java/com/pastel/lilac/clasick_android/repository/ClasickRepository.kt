package com.pastel.lilac.clasick_android.repository

import com.pastel.lilac.clasick_android.ClasickApi
import com.pastel.lilac.clasick_android.model.Music
import com.pastel.lilac.clasick_android.model.Playlist
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ClasickRepository(baseURL: String) : ClasickApi {
    private val apiClient = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()
        .create(ClasickApi::class.java)

    override suspend fun getPlayList(): List<Playlist> {
        return apiClient.getPlayList()
    }

    override suspend fun getMusic(): List<Music> {
        return apiClient.getMusic()
    }

}