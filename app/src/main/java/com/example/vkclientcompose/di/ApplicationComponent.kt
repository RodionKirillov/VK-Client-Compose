package com.example.vkclientcompose.di

import android.content.Context
import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.presentation.ViewModelFactory
import com.example.vkclientcompose.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}