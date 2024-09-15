package com.example.vkclientcompose.domain.usecases

import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.entity.PostComment
import com.example.vkclientcompose.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(feedPost: FeedPost): Flow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}