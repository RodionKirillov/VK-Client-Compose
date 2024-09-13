package com.example.vkclientcompose.domain

sealed class NewsFeedResult {

    object Error : NewsFeedResult()

    data class Success(
        val post: List<FeedPost>
    ) : NewsFeedResult()
}