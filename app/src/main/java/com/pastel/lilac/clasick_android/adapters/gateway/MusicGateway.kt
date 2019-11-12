package com.pastel.lilac.clasick_android.adapters.gateway

import com.pastel.lilac.clasick_android.adapters.gateway.interfaces.MusicRepository
import com.pastel.lilac.clasick_android.entity.Music

class MusicGateway(baseURL: String) :  MusicRepository {
    private val apiClient: MusicRepository =  MusicRepository.create(baseURL)

    override suspend fun getByMusicId(id: String): Music {
        return this.apiClient.getByMusicId(id)
    }

    override suspend fun getByPlaylistId(id: String): List<Music> {
        return this.apiClient.getByPlaylistId(id)
    }

    companion object Factory {
        fun create(baseURL: String): MusicRepository {
            return MusicGateway(baseURL)
        }
    }
}