package com.example.youtubeapi.ui.fragment.play_list

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.databinding.FragmentPlayListBinding
import com.example.youtubeapi.ui.base.BaseFragment
import com.example.youtubeapi.utils.Resource

class PlayListFragment :
    BaseFragment<FragmentPlayListBinding>(FragmentPlayListBinding::inflate) {

    private val viewModel: PlayListViewModel by viewModels()
    private val playlistsAdapter: PlaylistsAdapter by lazy { PlaylistsAdapter() }


    override fun setupViews() {
        super.setupViews()
        setupRecycler()
    }

    override fun setupObservers() {
        viewModel.getPlayLists().stateHandler(
            success = { data ->
                playlistsAdapter.submitList(data)
            },
            state = { state ->
                binding.pbLoading.isVisible = state is Resource.Loading
            }
        )
    }

    private fun setupRecycler() = with(binding.rvPlaylists) {
        adapter = playlistsAdapter
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}