package com.example.vkclientcompose.domain

import com.example.vkclientcompose.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.avatar_default_svgrepo_com,
    val commentText: String = " LONG COMMENT text",
    val publicationData: String = "14:00"
)
