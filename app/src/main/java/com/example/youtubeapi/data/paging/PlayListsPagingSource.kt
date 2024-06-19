package com.example.youtubeapi.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.api_services.YouTubeApi
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.utils.Constants

class PlayListsPagingSource(
    private val api: YouTubeApi,
    private val playlistId: String,
) : PagingSource<String, Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        return try {
            val response = api.getPlaylistItems(
                part = Constants.PART,
                maxResults = Constants.MAX_RESULTS,
                apiKey = BuildConfig.API_KEY,
                playlistId = playlistId,
                pageToken = params.key
            )
            val items = response.body()?.items ?: emptyList()
            val nextPageToken = response.body()?.nextPageToken

            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = nextPageToken
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Item>): String? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey
        }
    }
}