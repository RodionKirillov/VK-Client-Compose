package com.example.vkclientcompose.ui.theme

import com.example.vkclientcompose.domain.FeedPost
import com.example.vkclientcompose.domain.PostComment

sealed class HomeScreenState {

    object Initial: HomeScreenState()

    data class Posts(
        val posts: List<FeedPost>
    ): HomeScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): HomeScreenState()

}