package fr.isen.bellini.thegreatestcocktailapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import fr.isen.bellini.thegreatestcocktailapp.screens.CategoriesScreen
import fr.isen.bellini.thegreatestcocktailapp.screens.DetailCocktailScreen
import fr.isen.bellini.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val context = LocalContext.current

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
                    }

                ) { padding ->

                    //DetailCocktailScreen(padding)
                    CategoriesScreen(padding)
                }
            }
        }
    }
}
