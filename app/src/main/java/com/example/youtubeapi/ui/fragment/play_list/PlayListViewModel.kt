package com.example.youtubeapi.ui.fragment.play_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.data.repository.YouTubeRepository
import com.example.youtubeapi.utils.Resource

class PlayListViewModel( private val repository: YouTubeRepository
) : ViewModel() {

    fun getPlayLists(): LiveData<Resource<List<Item>>> {
        return repository.getPlayLists()
    }

}