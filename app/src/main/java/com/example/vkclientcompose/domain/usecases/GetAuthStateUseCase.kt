package com.example.vkclientcompose.domain.usecases

import com.example.vkclientcompose.domain.entity.AuthState
import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}