package com.example.vkclientcompose.presentation.comments

import com.example.vkclientcompose.domain.FeedPost
import com.example.vkclientcompose.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}