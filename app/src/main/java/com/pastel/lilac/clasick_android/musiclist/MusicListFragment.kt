package com.pastel.lilac.clasick_android.musiclist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.model.Music
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_music_list.*
import timber.log.Timber

class MusicListFragment : Fragment() {

    private lateinit var viewModel: MusicListViewModel
    private val musicListAdapter = GroupAdapter<GroupieViewHolder>()
    private val args: MusicListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_music_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coverImageView = view.findViewById<ImageView>(R.id.cover_image_view)
        val coverPath = args.coverPath
        viewModel = ViewModelProviders.of(this).get(MusicListViewModel::class.java)
        viewModel.fetchRemote()
        observeViewModel()
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        musicListRecyclerView.addItemDecoration(itemDecoration)
//        Glide.with(this)
//            .load(coverPath)
//            .onlyRetrieveFromCache(true)
//            .into(coverImageView)
    }

    private fun observeViewModel() {
        viewModel.musics.observe(this, Observer {
            initRecyclerView(it.toMusicListItem())
        })
    }

    private fun initRecyclerView(musicListItem: List<MusicListItem>) {
        musicListAdapter.apply {
            update(musicListItem)
            setOnItemClickListener(onItemClickListener)
        }
        musicListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = musicListAdapter
        }
    }

    fun List<Music>.toMusicListItem(): List<MusicListItem> {
        return this.map {
            MusicListItem(it)
        }
    }

    private val onItemClickListener = OnItemClickListener { item, view ->
        val action = MusicListFragmentDirections.actionMusicFragment()
        action.coverPath = args.coverPath
        Navigation.findNavController(activity!!, R.id.nav_host_fragment).navigate(action)
    }
}
