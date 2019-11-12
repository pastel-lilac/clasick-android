package com.pastel.lilac.clasick_android.musiclist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.adapters.gateway.interfaces.ClasickRepository
import com.pastel.lilac.clasick_android.entity.Music
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MusicListViewModel(application: Application) : AndroidViewModel(application) {
    var musics = MutableLiveData<List<Music>>()
    private val api = ClasickRepository(application.applicationContext.getString(R.string.BASE_URL))
    private val scope = CoroutineScope(Dispatchers.Main)
    private val pref = getApplication<Application>()
    fun fetchRemote() {
        scope.launch {
            try {
                musics.value = api.getMusic()
                musics.value?.let {
//                    pref.getSharedPreferences()
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
//    fun getJohannPachelbel(): String {
//
//    }
}