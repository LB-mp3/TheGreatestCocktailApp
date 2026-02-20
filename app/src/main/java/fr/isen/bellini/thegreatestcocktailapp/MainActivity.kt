package fr.isen.bellini.thegreatestcocktailapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.bellini.thegreatestcocktailapp.dataClasses.Drink
import fr.isen.bellini.thegreatestcocktailapp.network.RetrofitInstance
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

            val tabItems = listOf(randomItem, categoryItem, favoriteItem)

            TheGreatestCocktailAppTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            title = { Text("Loan's Application") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFFFCB274),
                                titleContentColor = Color.White
                            )
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
                            val cocktail = remember { mutableStateOf<Drink?>(null) }
                            LaunchedEffect(Unit) {
                                val response = RetrofitInstance.api.getRandomCocktail()
                                Log.d("network", "request ${response.drinks?.first()?.strDrink}")
                                cocktail.value = response.drinks?.first()
                            }
                            if (cocktail.value != null) {
                                DetailCocktailScreen(padding, cocktail.value)
                            } else {
                                Text("Loading")
                            }
                        }
                        composable(route = categoryItem.title) {
                            CategoriesScreen(padding)
                        }
                        composable(route = favoriteItem.title) {
                            FavoritesScreen(padding)
                        }
                    }
                }
            }
        }
    }
}