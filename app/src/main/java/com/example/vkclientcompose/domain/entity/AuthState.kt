package com.example.vkclientcompose.domain.entity

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object InitialState : AuthState()
}