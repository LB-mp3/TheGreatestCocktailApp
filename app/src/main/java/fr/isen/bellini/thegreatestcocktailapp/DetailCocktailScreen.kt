package fr.isen.bellini.thegreatestcocktailapp

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCocktailScreen() {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Cocktail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF5722),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Image ronde
            Image(
                painter = painterResource(id = R.drawable.cocktail_image),
                contentDescription = "Cocktail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "Cocktail Sunrise",
                style = MaterialTheme.typography.headlineMedium
            )

            Text("Category : Classic")
            Text("Verre : Highball glass")

            Spacer(modifier = Modifier.height(16.dp))

            // Card Ingredients
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFCdFAA)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("- Vodka", color = Color.Black)
                    Text("- Lemon juice", color = Color.Black)
                    Text("- Orange juice", color = Color.Black)
                    Text("- Grenadine", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card Recipe
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFCdFAA)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "Recipe",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Fill a shaker with ice cubes. Add 4 cl of vodka, 2 cl of fresh lemon juice, and 6 cl of orange juice. Pour into a highball glass filled with ice cubes, and slowly add a little grenadine to create a gradient effect. Serve well chilled!",
                        color = Color.Black
                    )
                }
            }
        }
    }
}
