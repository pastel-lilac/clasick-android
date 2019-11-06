package com.pastel.lilac.clasick_android.playlist

import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.databinding.ItemPlaylistBinding
import com.pastel.lilac.clasick_android.model.Playlist
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.GroupieViewHolder
import timber.log.Timber

class PlaylistItem(private val playlist: Playlist) : BindableItem<ItemPlaylistBinding>() {

    override fun getLayout() = R.layout.item_playlist

    override fun bind(viewBinding: ItemPlaylistBinding, position: Int) {
        viewBinding.playlist = playlist
        viewBinding.playListItem.setOnClickListener {
            Timber.d("click")
        }
    }

    override fun bind(viewHolder: GroupieViewHolder<ItemPlaylistBinding>, position: Int) {
        super.bind(viewHolder, position)
        viewHolder.itemView.setOnClickListener {
            Timber.d("dsasadas")
        }
    }

}