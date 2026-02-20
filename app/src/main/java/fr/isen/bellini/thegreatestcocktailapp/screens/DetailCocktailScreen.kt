package fr.isen.bellini.thegreatestcocktailapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import fr.bellini.thegreatestcocktailapp.dataClasses.Drink
import fr.isen.bellini.thegreatestcocktailapp.FavoritesManager

@Composable
fun DetailCocktailScreen(padding: PaddingValues, drink: Drink? = null) {

    val context = LocalContext.current
    val ingredients = drink?.ingredientList() ?: emptyList()
    val isFavorite = remember {
        mutableStateOf(
            if (drink?.idDrink != null) FavoritesManager.isFavorite(context, drink.idDrink)
            else false
        )
    }

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AsyncImage(
            model = drink?.strDrinkThumb,
            contentDescription = "Cocktail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
        )

        Text(
            text = drink?.strDrink ?: "",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily.Cursive,
            fontSize = 37.sp
        )

        // Bouton favori
        IconButton(onClick = {
            val id = drink?.idDrink ?: return@IconButton
            if (isFavorite.value) {
                FavoritesManager.removeFavorite(context, id)
            } else {
                FavoritesManager.addFavorite(context, id)
            }
            isFavorite.value = !isFavorite.value
        }) {
            Icon(
                imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color(0xFFFCB274)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = drink?.strCategory ?: "",
                fontSize = 20.sp,
                modifier = Modifier
                    .background(color = Color(0xFFFBCA9A), shape = RoundedCornerShape(50))
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = drink?.strAlcoholic ?: "",
                fontSize = 20.sp,
                modifier = Modifier
                    .background(color = Color(0xFFFBCA9A), shape = RoundedCornerShape(50))
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                fontWeight = FontWeight.Medium
            )
        }

        Text("Verre : ${drink?.strGlass ?: "N/A"}")

        ElevatedCard(
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFCdFAA)),
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
                ingredients.forEach { (ingredient, measure) ->
                    Text("- $measure $ingredient".trim())
                }
            }
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFCdFAA)),
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
                Text(drink?.strInstructions ?: "Aucune instruction disponible.")
            }
        }
    }
}