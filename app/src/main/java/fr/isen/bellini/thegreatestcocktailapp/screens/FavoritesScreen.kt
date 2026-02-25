package fr.isen.bellini.thegreatestcocktailapp.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.bellini.thegreatestcocktailapp.dataClasses.Drink
import fr.isen.bellini.thegreatestcocktailapp.DetailCocktailActivity
import fr.isen.bellini.thegreatestcocktailapp.managers.FavoritesManager
import fr.isen.bellini.thegreatestcocktailapp.network.RetrofitInstance

@Composable
fun FavoritesScreen(paddingValues: PaddingValues) {

    val context = LocalContext.current
    val drinks = remember { mutableStateOf<List<Drink>>(emptyList()) }

    LaunchedEffect(Unit) {
        val ids = FavoritesManager.getFavorites(context)
        val result = ids.mapNotNull { id ->
            runCatching {
                RetrofitInstance.api.getDrinkDetail(id).drinks?.first()
            }.getOrNull()
        }
        drinks.value = result
    }

    val orangeGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF5A2400),
            Color(0xFFB34700),
            Color(0xFFFF9E4A)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(orangeGradient)
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (drinks.value.isEmpty()) {
            item {
                Text(
                    text = "No favourites at the moment.",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            }
        }
        items(drinks.value) { drink ->
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(context, DetailCocktailActivity::class.java)
                        intent.putExtra("drinkId", drink.idDrink)
                        context.startActivity(intent)
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x33FFFFFF)),
                border = BorderStroke(1.dp, Color(0xAAFFFFFF)),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Text(
                    text = drink.strDrink ?: "",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}