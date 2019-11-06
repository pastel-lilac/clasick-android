package com.pastel.lilac.clasick_android.music

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.model.Music
import com.pastel.lilac.clasick_android.repository.ClasickRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MusicViewModel(application: Application) : AndroidViewModel(application) {
    var music = MutableLiveData<List<Music>>()
    private val api = ClasickRepository(application.applicationContext.getString(R.string.BASE_URL))
    private val scope = CoroutineScope(Dispatchers.Main)

    fun fetchRemote() {
        scope.launch {
            try {
                music.value = api.getMusic()
                Log.d("TAG", music.value.toString())
            } catch (e: Exception) {
                e.stackTrace
                Log.d("TAGTAG", e.toString())
            }
        }
    }
}