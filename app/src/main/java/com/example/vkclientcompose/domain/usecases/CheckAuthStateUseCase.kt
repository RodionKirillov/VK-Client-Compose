package com.example.vkclientcompose.domain.usecases

import com.example.vkclientcompose.domain.repository.NewsFeedRepository

class CheckAuthStateUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() = repository.checkAuthState()
}