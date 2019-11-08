package com.pastel.lilac.clasick_android.music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.databinding.FragmentMusicBinding
import timber.log.Timber

class MusicFragment : Fragment() {
    private lateinit var viewModel: MusicViewModel
    private lateinit var binding: FragmentMusicBinding

    // リスト表示
    // Glideキャッシュ確認
    // navigationで値渡し確認(coverPath Url)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = DataBindingUtil.inflate<FragmentMusicBinding>(layoutInflater, R.layout.fragment_music, container, false)
        viewModel = ViewModelProviders.of(this).get(MusicViewModel::class.java)
        this.observeViewModel()
        val coverPath = MusicFragmentArgs.fromBundle(arguments!!).coverPath
//        val glideCache = Glide.with(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchRemote()
    }

    private fun observeViewModel() {
        viewModel.musics.observe(this, Observer {
            this.binding.setVariable(androidx.databinding.library.baseAdapters.BR.music, it)
        })
    }
}
