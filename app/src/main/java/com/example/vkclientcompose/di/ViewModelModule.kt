package com.example.vkclientcompose.di

import androidx.lifecycle.ViewModel
import com.example.vkclientcompose.presentation.comments.CommentsViewModel
import com.example.vkclientcompose.presentation.main.MainViewModel
import com.example.vkclientcompose.presentation.news.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    @Binds
    fun bindNewsFeedViewModule(viewModel: NewsFeedViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModule(viewModel: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    @Binds
    fun bindCommentsViewModule(viewModel: CommentsViewModel): ViewModel
}