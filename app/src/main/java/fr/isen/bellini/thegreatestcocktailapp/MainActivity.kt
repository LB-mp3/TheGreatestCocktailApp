package fr.isen.bellini.thegreatestcocktailapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.bellini.thegreatestcocktailapp.screens.BottomAppBar
import fr.isen.bellini.thegreatestcocktailapp.screens.CategoriesScreen
import fr.isen.bellini.thegreatestcocktailapp.screens.DetailCocktailScreen
import fr.isen.bellini.thegreatestcocktailapp.screens.FavoritesScreen
import fr.isen.bellini.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val context = LocalContext.current
            val navController = rememberNavController()

            val randomItem = TabBarItem(
                title = stringResource(R.string.tab_item_random),
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )

            val categoryItem = TabBarItem(
                title = stringResource(R.string.tab_item_category),
                selectedIcon = Icons.Filled.Menu,
                unselectedIcon = Icons.Outlined.Menu
            )

            val favoriteItem = TabBarItem(
                title = stringResource(R.string.tab_item_favorite),
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.Favorite
            )

            val tabItems = listOf(
                randomItem,
                categoryItem,
                favoriteItem
            )

            TheGreatestCocktailAppTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            title = { Text("Loan's Application") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFFFCB274),
                                titleContentColor = Color.White
                            ),
                            actions = {
                                IconButton(onClick = {
                                    Toast.makeText(
                                        context,
                                        "Added to favorites",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.FavoriteBorder,
                                        contentDescription = "Favorite",
                                        tint = Color.White
                                    )
                                }
                            }
                        )
                    },

                    bottomBar = {
                        BottomAppBar(tabItems, navController)
                    }

                ) { padding ->

                    NavHost(
                        navController = navController,
                        startDestination = randomItem.title
                    ) {
                        composable(route = randomItem.title) {
                            DetailCocktailScreen(
                                padding
                            )
                        }
                        composable(route = categoryItem.title) {
                            CategoriesScreen(
                                padding
                            )
                        }
                        composable(route = favoriteItem.title) {
                            FavoritesScreen(
                                padding
                            )
                        }
                    }

                }
            }
        }
    }
}
