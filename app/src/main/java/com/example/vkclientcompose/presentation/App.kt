package com.example.vkclientcompose.presentation

import android.app.Application
import com.example.vkclientcompose.di.ApplicationComponent
import com.example.vkclientcompose.di.DaggerApplicationComponent
import com.example.vkclientcompose.domain.entity.FeedPost

class App : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            this,
        )
    }
}