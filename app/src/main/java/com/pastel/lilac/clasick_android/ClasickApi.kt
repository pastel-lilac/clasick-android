package com.pastel.lilac.clasick_android

import com.pastel.lilac.clasick_android.model.Music
import com.pastel.lilac.clasick_android.model.Playlist
import retrofit2.http.GET

interface ClasickApi {
    @GET("playlists")
    suspend fun getPlayList(): List<Playlist>

    @GET("musics")
    suspend fun getMusic(): Music
}