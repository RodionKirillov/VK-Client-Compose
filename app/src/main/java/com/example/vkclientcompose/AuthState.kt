package com.example.vkclientcompose

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object InitialState : AuthState()
}