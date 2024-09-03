package com.example.vkclientcompose.ui.theme


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkclientcompose.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                var selectedItemPosition by rememberSaveable {
                    mutableIntStateOf(0)
                }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPosition == index,
                        onClick = { selectedItemPosition = index },
                        icon = {
                            Icon(
                                imageVector = if (selectedItemPosition == index) item.selectedIcon
                                else item.unselectedIcon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = item.titleResId),
                                fontWeight = if (selectedItemPosition == index) FontWeight.Bold
                                else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            indicatorColor = Blue500,
                        )
                    )
                }
            }
        }
    ) {
        val feedPosts = viewModel.feedPosts.observeAsState(mutableListOf())

        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = feedPosts.value,
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
                        onViewsClickListener = { statisticItem ->
                            viewModel.updateCount(
                                feedPost = model,
                                item = statisticItem
                            )
                        },
                        onShareClickListener = { statisticItem ->
                            viewModel.updateCount(
                                feedPost = model,
                                item = statisticItem
                            )
                        },
                        onCommentClickListener = { statisticItem ->
                            viewModel.updateCount(
                                feedPost = model,
                                item = statisticItem
                            )
                        },
                        onLikeClickListener = { statisticItem ->
                            viewModel.updateCount(
                                feedPost = model,
                                item = statisticItem
                            )
                        },
                    )
                }
            }
        }
    }
}