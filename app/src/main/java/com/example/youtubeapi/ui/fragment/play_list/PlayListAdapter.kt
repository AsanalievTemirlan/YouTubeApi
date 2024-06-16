package com.example.youtubeapi.ui.fragment.play_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.databinding.ItemPlaylistsBinding

class PlaylistsAdapter(private var playlists: ArrayList<Item>) :
    ListAdapter<PlaylistsAdapter.ViewHolder>(DiffCallback(Item)) {

    inner class ViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(playlists: Item) = with(binding) {
            tvNamePlaylist.text = playlists.snippet.title
            tvQuantityVideo.text = playlists.contentDetails.itemCount.toString()
            imgPlaylists.load(playlists.snippet.thumbnails.medium.url) {
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = playlists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playlists[position])
    }
}

class DiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem && oldItem == newItem
}