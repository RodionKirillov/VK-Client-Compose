package com.example.vkclientcompose.domain.usecases

import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.repository.NewsFeedRepository

class DeletePostUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) = repository.deletePost(feedPost)
}