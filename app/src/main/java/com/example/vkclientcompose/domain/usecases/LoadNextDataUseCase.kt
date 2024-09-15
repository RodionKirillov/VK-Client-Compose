package com.example.vkclientcompose.domain.usecases

import com.example.vkclientcompose.domain.repository.NewsFeedRepository
import javax.inject.Inject

class LoadNextDataUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() = repository.loadNextData()
}