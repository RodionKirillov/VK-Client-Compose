package com.example.vkclientcompose.presentation.news

import com.example.vkclientcompose.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()

    data class Posts(
        val posts: List<FeedPost>
    ): NewsFeedScreenState()
}