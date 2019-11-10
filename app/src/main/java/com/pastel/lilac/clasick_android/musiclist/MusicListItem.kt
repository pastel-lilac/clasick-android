package com.pastel.lilac.clasick_android.musiclist

import com.pastel.lilac.clasick_android.R
import com.pastel.lilac.clasick_android.databinding.ItemMusiclistBinding
import com.pastel.lilac.clasick_android.model.Music
import com.xwray.groupie.databinding.BindableItem

class MusicListItem(private val musiclist: Music) : BindableItem<ItemMusiclistBinding>() {

    override fun getLayout() = R.layout.item_musiclist

    override fun bind(viewBinding: ItemMusiclistBinding, position: Int) {
        viewBinding.music = musiclist
    }
}