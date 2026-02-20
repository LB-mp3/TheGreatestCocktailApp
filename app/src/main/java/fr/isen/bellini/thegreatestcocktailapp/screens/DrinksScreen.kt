package fr.isen.bellini.thegreatestcocktailapp.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import fr.isen.bellini.thegreatestcocktailapp.dataClasses.DrinkPreview
import fr.isen.bellini.thegreatestcocktailapp.DetailCocktailActivity
import fr.isen.bellini.thegreatestcocktailapp.network.RetrofitInstance

@Composable
fun DrinksScreen(paddingValues: PaddingValues, category: String) {

    val context = LocalContext.current
    val drinks = remember { mutableStateOf<List<DrinkPreview>>(emptyList()) }

    LaunchedEffect(category) {
        val response = RetrofitInstance.api.getDrinksByCategory(category)
        drinks.value = response.drinks ?: emptyList()
        Log.d("network", "drinks loaded: ${drinks.value.size}")
    }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(drinks.value) { drink ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(context, DetailCocktailActivity::class.java)
                        intent.putExtra("drinkId", drink.idDrink)
                        context.startActivity(intent)
                        Log.d("tag", "click on ${drink.strDrink}")
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFCdFAA)
                )
            ) {
                //J'ai mis un Row ici pour pouvoir afficher les images à cote du texte
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        model = drink.strDrinkThumb,
                        contentDescription = drink.strDrink,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text(
                        text = drink.strDrink ?: "",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}