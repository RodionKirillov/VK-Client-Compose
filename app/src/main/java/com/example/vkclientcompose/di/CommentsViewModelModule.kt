package com.example.vkclientcompose.di

import androidx.lifecycle.ViewModel
import com.example.vkclientcompose.presentation.comments.CommentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CommentsViewModelModule {

    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    @Binds
    fun bindCommentsViewModule(viewModel: CommentsViewModel): ViewModel
}