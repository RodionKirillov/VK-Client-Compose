package com.example.vkclientcompose.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vkclientcompose.domain.FeedPost
import com.example.vkclientcompose.domain.StatisticItem

class NewsFeedViewModel : ViewModel() {

    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(5) {
            add(
                FeedPost().copy(
                    id = it,
                    contentText = "Content $it"
                )
            )
        }
    }
    private val initialState = NewsFeedScreenState.Posts(posts = initialList)

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState


    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(posts = newPosts)
    }

    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(posts = oldPosts)
    }
}













