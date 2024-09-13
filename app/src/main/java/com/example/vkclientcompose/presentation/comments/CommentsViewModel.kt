package com.example.vkclientcompose.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vkclientcompose.data.repository.NewsFeedRepositoryImpl
import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)
    private val getCommentsUseCase = GetCommentsUseCase(repository = repository)

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            ) as CommentsScreenState
        }
}