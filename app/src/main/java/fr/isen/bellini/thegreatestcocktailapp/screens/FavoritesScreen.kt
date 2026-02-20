package fr.isen.bellini.thegreatestcocktailapp.screens
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fr.bellini.thegreatestcocktailapp.dataClasses.Drink
import fr.isen.bellini.thegreatestcocktailapp.DetailCocktailActivity
import fr.isen.bellini.thegreatestcocktailapp.FavoritesManager
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

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (drinks.value.isEmpty()) {
            item {
                Text("No favourites at the moment.", modifier = Modifier.padding(16.dp))
            }
        }
        items(drinks.value) { drink ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(context, DetailCocktailActivity::class.java)
                        intent.putExtra("drinkId", drink.idDrink)
                        context.startActivity(intent)
                    },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFCdFAA))
            ) {
                Text(
                    text = drink.strDrink ?: "",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}