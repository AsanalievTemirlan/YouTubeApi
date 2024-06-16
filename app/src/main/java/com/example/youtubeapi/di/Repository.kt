package com.example.youtubeapi.di

import com.example.youtubeapi.data.repository.YouTubeRepository
import org.koin.core.module.Module
import org.koin.dsl.module


val repositoryModule: Module = module {
    single { YouTubeRepository(get()) }
}