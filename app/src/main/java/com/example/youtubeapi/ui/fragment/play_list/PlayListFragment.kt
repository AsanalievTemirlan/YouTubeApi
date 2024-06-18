package com.example.youtubeapi.ui.fragment.play_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.databinding.FragmentPlayListBinding
import com.example.youtubeapi.utils.Resource

class PlayListFragment : Fragment() {

    private lateinit var binding: FragmentPlayListBinding
    private val viewModel: PlayListViewModel by viewModels()
    private var playlists = arrayListOf<Item>()
    private val playlistsAdapter: PlaylistsAdapter by lazy {
        PlaylistsAdapter(playlists)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getPlayLists().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    playlistsAdapter.submitList(result.data)
                }
                is Resource.Error -> {

                }
            }
            binding.pbLoading.isVisible = false
        }
    }

    private fun initialize() {
        binding.rvPlaylists.adapter = playlistsAdapter
    }
}
