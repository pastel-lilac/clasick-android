package com.pastel.lilac.clasick_android.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.entity.Playlist
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_playlist.*

class PlaylistFragment : Fragment() {

    private val playlistAdapter = GroupAdapter<GroupieViewHolder>()

//    override fun onPlaylistClicked(v: View) {
//        groupAdapter.apply {
//            setOnItemClickListener(onItemClickListener)
//        }
//    }

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
        playlistAdapter.apply {
            // update by new value = previous value reset
            update(playlistItem)
            setOnItemClickListener(onItemClickListener)
        }
        playlistRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = playlistAdapter
        }
    }

    fun List<Playlist>.toPlaylistItem() : List<PlaylistItem> {
        return this.map {
            PlaylistItem(it)
        }
    }

    private val onItemClickListener = OnItemClickListener { item, view ->
        val index = this.playlistAdapter.getAdapterPosition(item)
        val coverImage = requireActivity().findViewById<ImageView>(R.id.cover_image_view)
        val coverPath = viewModel.playlists.value?.get(index)?.coverPath ?: return@OnItemClickListener
        Glide.with(this)
            .load(coverPath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(coverImage)
        val action = PlaylistFragmentDirections.actionMusicListFragment()
        action.coverPath = coverPath
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(action)
    }
}
