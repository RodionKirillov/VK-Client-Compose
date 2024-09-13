package com.example.vkclientcompose.domain.entity

data class PostComment(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val commentText: String,
    val publicationData: String,
)
