package com.example.vkclientcompose.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkclientcompose.data.repository.NewsFeedRepositoryImpl
import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.usecases.ChangeLikeStatusUseCase
import com.example.vkclientcompose.domain.usecases.DeletePostUseCase
import com.example.vkclientcompose.domain.usecases.GetRecommendationsUseCase
import com.example.vkclientcompose.domain.usecases.LoadNextDataUseCase
import com.example.vkclientcompose.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        Log.d("LOG_TAG", "Exception caught by exception handler")
    }

        private val repository = NewsFeedRepositoryImpl(application)
    private val getRecommendationsUseCase = GetRecommendationsUseCase(repository = repository)
    private val loadNextDataUseCase = LoadNextDataUseCase(repository = repository)
    private val changeLikeStatusUseCase = ChangeLikeStatusUseCase(repository = repository)
    private val deletePostUseCase = DeletePostUseCase(repository = repository)

    private val recommendationFlow = getRecommendationsUseCase()

    private val loadNextDataEvents = MutableSharedFlow<Unit>()
    private val loadNextDataFlow = flow {
        loadNextDataEvents.collect {
            emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationFlow.value,
                    nextDataIsLoading = true
                )
            )
        }
    }

    val screenState = recommendationFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(posts = it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)

    fun loadNextRecommendation() {
        viewModelScope.launch {
            loadNextDataEvents.emit(Unit)
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost = feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(feedPost = feedPost)
        }
    }
}