package com.example.vkclientcompose.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ScaffoldTest() {
    val listItems = listOf(
        NavItem("Favorite", Icons.Filled.Favorite, Icons.Outlined.Favorite),
        NavItem("Edit", Icons.Filled.Edit, Icons.Outlined.Edit),
        NavItem("Delete", Icons.Filled.Delete, Icons.Outlined.Delete),
    )
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                listItems.forEachIndexed { index, navItem ->
                    NavigationDrawerItem(
                        label = { Text(text = navItem.title) },
                        selected = selectedItemIndex == index,
                        icon = {
                            Icon(
                                imageVector = if (selectedItemIndex == index) navItem.selectedIcon
                                else navItem.unselectedIcon, contentDescription = navItem.title
                            )
                        },
                        onClick = { selectedItemIndex = index })
                }
            }
        }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Simple TopAppBar") },
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Menu, "Localized description")
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    listItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = { selectedItemIndex = index },
                            icon = {
                                Icon(
                                    imageVector = if (selectedItemIndex == index) item.selectedIcon
                                    else item.unselectedIcon, contentDescription = item.title
                                )
                            },
                            label = {
                                Text(text = item.title)
                            }
                        )
                    }
                }
            }
        ) {
            Text(text = "some text", modifier = Modifier.padding(it))
        }
    }
}