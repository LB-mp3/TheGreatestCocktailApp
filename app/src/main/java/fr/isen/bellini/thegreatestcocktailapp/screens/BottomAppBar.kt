package fr.isen.bellini.thegreatestcocktailapp.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import fr.isen.bellini.thegreatestcocktailapp.TabBarItem

@Composable
fun BottomAppBar(
    items: List<TabBarItem>,
    navController: NavController
) {

    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar {
        items.forEachIndexed { index, item ->

            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(item.title)
                },
                icon = {
                    TabBarIcon(
                        isSelected= selectedTabIndex == index,
                        item.selectedIcon,
                        item.selectedIcon,
                        item.title
                    )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}

@Composable
fun TabBarIcon(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String
) {
    Icon(
        imageVector = if (isSelected) selectedIcon else unselectedIcon,
        contentDescription = title
    )
}
