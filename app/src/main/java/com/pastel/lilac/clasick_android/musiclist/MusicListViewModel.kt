package com.pastel.lilac.clasick_android.musiclist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.model.Music
import com.pastel.lilac.clasick_android.repository.ClasickRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MusicListViewModel(application: Application) : AndroidViewModel(application) {
    var musics = MutableLiveData<List<Music>>()
    private val api = ClasickRepository(application.applicationContext.getString(R.string.BASE_URL))
    private val scope = CoroutineScope(Dispatchers.Main)
    fun fetchRemote() {
        scope.launch {
            try {
                musics.value = api.getMusic()
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}