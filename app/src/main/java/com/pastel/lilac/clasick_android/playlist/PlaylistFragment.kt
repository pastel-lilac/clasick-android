package com.pastel.lilac.clasick_android.playlist

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.model.Playlist
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_playlist.*
import java.sql.Driver

class PlaylistFragment : Fragment(), PlaylistClickListener {
    override fun onPlaylistClicked(v: View) {
        Toast.makeText(context, "dsdfdsa", Toast.LENGTH_SHORT).show()
    }

    private lateinit var viewModel: PlaylistViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        viewModel.fetchRemote()
        observeViewModel()
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        playlistRecyclerView.addItemDecoration(itemDecoration)
    }

    private fun observeViewModel() {
        viewModel.playlists.observe(this, Observer {
            initRecyclerView(it.toPlaylistItem())
        })
    }

    private fun initRecyclerView(playlistItem: List<PlaylistItem>) {
        val pAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(playlistItem)
        }
        playlistRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = pAdapter
        }
    }

    fun List<Playlist>.toPlaylistItem() : List<PlaylistItem> {
        return this.map {
            PlaylistItem(it)
        }
    }
}
