package com.example.vkclientcompose.presentation.main

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object InitialState : AuthState()
}