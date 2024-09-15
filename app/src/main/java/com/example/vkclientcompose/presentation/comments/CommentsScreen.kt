package com.example.vkclientcompose.presentation.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vkclientcompose.R
import com.example.vkclientcompose.domain.entity.FeedPost
import com.example.vkclientcompose.domain.entity.PostComment
import com.example.vkclientcompose.presentation.App
import com.example.vkclientcompose.presentation.ViewModelFactory
import com.example.vkclientcompose.presentation.getApplicationComponent


@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    onBackPressed: () -> Unit
) {
    val component = getApplicationComponent()
        .getCommentsScreenComponentFactory()
        .create(feedPost = feedPost)

    val viewModel: CommentsViewModel = viewModel(factory = component.getViewModelFactory())
    val screenState = viewModel.screenState.collectAsState(CommentsScreenState.Initial)

    CommentsScreenContent(
        screenState = screenState,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommentsScreenContent(
    screenState: State<CommentsScreenState>,
    onBackPressed: () -> Unit
) {
    val currentState = screenState.value

    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    title = {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = stringResource(R.string.comments_title)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { onBackPressed() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 72.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = currentState.comments,
                    key = { it.id }
                ) { comment ->
                    CommentItem(comment = comment)
                }
            }
        }
    }
}

@Composable
private fun CommentItem(
    comment: PostComment
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(40.dp)

                .clip(CircleShape)
                .background(color = Color.White),
            model = comment.authorAvatarUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = comment.authorName,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.commentText,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.publicationData,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp,
            )
        }
    }
}