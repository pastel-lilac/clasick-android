package com.pastel.lilac.clasick_android.adapters.gateway

import com.pastel.lilac.clasick_android.adapters.gateway.interfaces.PlaylistRepository
import com.pastel.lilac.clasick_android.entity.Playlist

class PlaylistGateway(baseURL: String) : PlaylistRepository {
    private val apiClient: PlaylistRepository =  PlaylistRepository.create(baseURL)

    override suspend fun get(userIdentifier: String): List<Playlist> {
        return apiClient.get(userIdentifier)
    }

    companion object Factory {
        fun create(baseURL: String): PlaylistRepository {
            return PlaylistGateway(baseURL)
        }
    }
}