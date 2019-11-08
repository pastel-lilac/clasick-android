package com.pastel.lilac.clasick_android.playlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.model.Playlist
import com.pastel.lilac.clasick_android.repository.ClasickRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {
    var playlists = MutableLiveData<List<Playlist>>()
    private val api = ClasickRepository(application.applicationContext.getString(R.string.BASE_URL))
    private val scope = CoroutineScope(Dispatchers.Main)

    fun fetchRemote() {
        scope.launch {
            try {
                val result =  api.getPlayList()
                when(playlists.value?.size) {
                    0 -> playlists.value = result
                    result.size -> {
                    }
                    else -> playlists.value = result
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}