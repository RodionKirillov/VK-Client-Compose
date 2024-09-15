package com.example.vkclientcompose.domain.usecases

import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.repository.NewsFeedRepository
import javax.inject.Inject

class ChangeLikeStatusUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) = repository.changeLikeStatus(feedPost)
}