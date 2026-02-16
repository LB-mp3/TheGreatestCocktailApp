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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Category {
    COCKTAIL,
    NON_ALCOHOLIC
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCocktailScreen() {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Application de Loan") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFB9A4D),
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Image ronde
            Image(
                painter = painterResource(id = R.drawable.cocktail_image),
                contentDescription = "Cocktail",
                contentScale = ContentScale.Crop,
                alignment = Alignment.CenterStart,
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
            )

            // Title
            Text(
                text = "Cocktail Sunrise",
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.Cursive,
                fontSize = 37.sp
            )

            // Chips centrées
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                AssistChip(
                    onClick = {},
                    label = { Text("Cocktail") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color(0xFFFCF3B9),
                        labelColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                AssistChip(
                    onClick = {},
                    label = { Text("Non alcoholic") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color(0xFFFCF3B9),
                        labelColor = Color.Black
                    )
                )
            }

            Text("Verre : Highball glass")

            // Card Ingredients
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFCdFAA)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "Ingredients :",
                        fontSize = 26.sp,
                        color = Color.Black,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("- Vodka")
                    Text("- Lemon juice")
                    Text("- Orange juice")
                    Text("- Grenadine")
                }
            }

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
                        text = "Recipe :",
                        fontSize = 26.sp,
                        color = Color.Black,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Fill a shaker with ice cubes. Add 4 cl of vodka, 2 cl of fresh lemon juice, and 6 cl of orange juice. Pour into a highball glass filled with ice cubes, and slowly add a little grenadine to create a gradient effect. Serve well chilled!"
                    )
                }
            }
        }
    }
}
