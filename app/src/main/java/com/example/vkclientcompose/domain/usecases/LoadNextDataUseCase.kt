package com.example.vkclientcompose.domain.usecases

import com.example.vkclientcompose.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() = repository.loadNextData()
}