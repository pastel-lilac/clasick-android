package com.pastel.lilac.clasick_android.music

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.AudioListener
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.util.Util

import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {
    private lateinit var viewModel: MusicViewModel
    private lateinit var binding: FragmentMusicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

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
        val exoPlayer = ExoPlayerFactory.newSimpleInstance(context)
        val dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, context?.packageName))
        val mediaSource = ProgressiveMediaSource
            .Factory(dataSourceFactory)
            .createMediaSource(Uri.parse("httpmp3"))
        // 再生
        exoPlayer.prepare(mediaSource)
        // 状態監視
        exoPlayer.addAudioListener(object : AudioListener {
            // タイムラインが変わった、再生が始まった、再生が止まった、再生がエラーになったなどの情報はここで取れます
        })
        // 再生
        exoPlayer.playWhenReady = true

        // ポーズ
        exoPlayer.playWhenReady = false

        // 音量調整
        exoPlayer.volume = 0f

        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        seekBar.max = 200
        seekBar.progress = 10

    }

    private fun observeViewModel() {
        viewModel.musics.observe(this, Observer {
            binding.music = it[0]
        })
    }
}
