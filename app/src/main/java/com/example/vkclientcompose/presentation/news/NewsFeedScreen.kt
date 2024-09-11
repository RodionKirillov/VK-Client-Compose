package com.example.vkclientcompose.presentation.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkclientcompose.domain.FeedPost
import com.example.vkclientcompose.ui.theme.VkBlue
import com.example.vkclientcompose.ui.theme.VkGreyBackground

@Composable
fun NewsFeedScreen(
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit

) {
    val viewModel: NewsFeedViewModel = viewModel()

    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts,
                onCommentClickListener = onCommentClickListener,
                nextDataIsLoading = currentState.nextDataIsLoading
            )
        }

        is NewsFeedScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = VkBlue)
            }
        }

        NewsFeedScreenState.Initial -> {

        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FeedPosts(
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentClickListener: (FeedPost) -> Unit,
    nextDataIsLoading: Boolean
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues = paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { feedPost -> feedPost.id }
        ) { model ->

            val dismissThresholds = with(LocalDensity.current) {
                LocalConfiguration.current.screenWidthDp.dp.toPx() * 0.5f
            }

            val dismissBoxState = rememberSwipeToDismissBoxState(
                positionalThreshold = { dismissThresholds },
                confirmValueChange = { value ->
                    val isDismissed = value in setOf(
                        SwipeToDismissBoxValue.StartToEnd,
                        SwipeToDismissBoxValue.EndToStart
                    )
                    if (isDismissed) viewModel.remove(model)

                    return@rememberSwipeToDismissBoxState isDismissed
                }
            )

            SwipeToDismissBox(
                modifier = Modifier.animateItemPlacement(),
                state = dismissBoxState,
                enableDismissFromEndToStart = true,
                enableDismissFromStartToEnd = false,
                backgroundContent = {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.Red.copy(alpha = 0.7f))
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "DELETE ${model.id}",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    }
                }
            ) {
                PostCard(
                    feedPost = model,
                    onCommentClickListener = {
                        onCommentClickListener(model)
                    },
                    onLikeClickListener = { _ ->
                        viewModel.changeLikeStatus(model)
                    },
                )
            }
        }
        item {
            if (nextDataIsLoading) {
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = VkBlue)
                }
            } else {
                SideEffect {
                    viewModel.loadNextRecommendation()
                }
            }
        }
    }
}