package com.example.vkclientcompose.domain.usecases

import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.entity.PostComment
import com.example.vkclientcompose.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow

class GetCommentsUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(feedPost: FeedPost): Flow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}