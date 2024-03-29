package com.pastel.lilac.clasick_android.playlist

import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.databinding.ItemPlaylistBinding
import com.pastel.lilac.clasick_android.entity.Playlist
import com.xwray.groupie.databinding.BindableItem

class PlaylistItem(private val playlist: Playlist) : BindableItem<ItemPlaylistBinding>() {

    override fun getLayout() = R.layout.item_playlist

    override fun bind(viewBinding: ItemPlaylistBinding, position: Int) {
        viewBinding.playlist = playlist
    }
}