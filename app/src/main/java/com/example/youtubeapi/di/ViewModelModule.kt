package com.example.youtubeapi.di

import com.example.youtubeapi.ui.fragment.play_list.PlayListViewModel
import com.example.youtubeapi.ui.fragment.player.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule : Module = module {
    viewModel {
        PlayListViewModel(get())
    }
}