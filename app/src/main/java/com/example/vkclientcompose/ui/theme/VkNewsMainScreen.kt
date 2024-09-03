package com.example.vkclientcompose.ui.theme


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.vkclientcompose.MainViewModel
import com.example.vkclientcompose.domain.FeedPost

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
        val feedPost = viewModel.feedPost.observeAsState(FeedPost())
        PostCard(
            modifier = Modifier
                .padding(it)
                .padding(8.dp),
            feedPost = feedPost.value,
            onViewsClickListener = viewModel::updateCount,
            onShareClickListener = viewModel::updateCount,
            onCommentClickListener = viewModel::updateCount,
            onLikeClickListener = viewModel::updateCount,
        )
    }
}