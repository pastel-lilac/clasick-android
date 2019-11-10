package com.pastel.lilac.clasick_android.music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {
    private lateinit var viewModel: MusicViewModel
    private lateinit var binding: FragmentMusicBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)
        this.binding = FragmentMusicBinding.bind(view)
        viewModel = ViewModelProviders.of(this).get(MusicViewModel::class.java)
        this.observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchRemote()
        val coverImageView = view.findViewById<ImageView>(R.id.coverImageView)
        val args: MusicFragmentArgs by navArgs()
        val coverPath = args.coverPath
        Glide.with(this)
            .load(coverPath)
            .onlyRetrieveFromCache(true)
            .into(coverImageView)

    }

    private fun observeViewModel() {
        viewModel.musics.observe(this, Observer {
            binding.music = it[0]
        })
    }
}
