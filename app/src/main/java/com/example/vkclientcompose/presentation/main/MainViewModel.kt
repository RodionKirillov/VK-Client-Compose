package com.example.vkclientcompose.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkclientcompose.data.repository.NewsFeedRepositoryImpl
import com.example.vkclientcompose.domain.usecases.CheckAuthStateUseCase
import com.example.vkclientcompose.domain.usecases.GetAuthStateUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)
    private val getAuthStateUseCase = GetAuthStateUseCase(repository = repository)
    private val checkAuthStateUseCase = CheckAuthStateUseCase(repository = repository)

    val authState = getAuthStateUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase()
        }
    }
}