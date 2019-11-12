package com.pastel.lilac.clasick_android

import com.pastel.lilac.clasick_android.entity.Music
import com.pastel.lilac.clasick_android.entity.Playlist
import retrofit2.http.GET

interface ClasickApi {
    @GET("playlists")
    suspend fun getPlayList(): List<Playlist>

    @GET("musics")
    suspend fun getMusic(): List<Music>
}