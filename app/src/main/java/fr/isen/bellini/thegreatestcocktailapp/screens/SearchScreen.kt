package fr.isen.bellini.thegreatestcocktailapp.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import fr.bellini.thegreatestcocktailapp.dataClasses.Drink
import fr.isen.bellini.thegreatestcocktailapp.DetailCocktailActivity
import fr.isen.bellini.thegreatestcocktailapp.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(paddingValues: PaddingValues) {

    val context = LocalContext.current
    val query = remember { mutableStateOf("") }
    val searchType = remember { mutableStateOf("name") }
    val drinks = remember { mutableStateOf<List<Drink>>(emptyList()) }
    val isLoading = remember { mutableStateOf(false) }
    val noResults = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val orangeGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF5A2400),
            Color(0xFFB34700),
            Color(0xFFFF9E4A)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(orangeGradient)
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { searchType.value = "name" },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (searchType.value == "name") Color(0x66FFFFFF) else Color(0x33FFFFFF)
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text("By name", color = Color.White)
            }
            Button(
                onClick = { searchType.value = "ingredient" },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (searchType.value == "ingredient") Color(0x66FFFFFF) else Color(0x33FFFFFF)
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text("By ingredient", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ici pour la barre de recherche
        OutlinedTextField(
            value = query.value,
            onValueChange = { query.value = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0x33FFFFFF),
                focusedContainerColor   = Color(0x44FFFFFF),
                unfocusedBorderColor    = Color(0x66FFFFFF),
                focusedBorderColor      = Color.White,
                cursorColor             = Color.White,
                unfocusedPlaceholderColor = Color(0xCCFFFFFF),
                focusedPlaceholderColor   = Color.White,
                unfocusedTextColor      = Color.White,
                focusedTextColor        = Color.White,
                unfocusedTrailingIconColor = Color.White,
                focusedTrailingIconColor   = Color.White,
            ),
            placeholder = {
                Text(if (searchType.value == "name") "Ex: Mojito" else "Ex: Vodka")
            },
            trailingIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        if (query.value.isBlank()) return@launch
                        isLoading.value = true
                        noResults.value = false
                        try {
                            drinks.value = if (searchType.value == "name") {
                                RetrofitInstance.api.searchByName(query.value).drinks ?: emptyList()
                            } else {
                                val previews = RetrofitInstance.api.searchByIngredient(query.value).drinks ?: emptyList()
                                previews.mapNotNull { preview ->
                                    runCatching {
                                        RetrofitInstance.api.getDrinkDetail(preview.idDrink ?: return@mapNotNull null).drinks?.first()
                                    }.getOrNull()
                                }
                            }
                            noResults.value = drinks.value.isEmpty()
                        } catch (e: Exception) {
                            Log.e("search", "error: ${e.message}")
                            drinks.value = emptyList()
                            noResults.value = true
                        }
                        isLoading.value = false
                    }
                }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        when {
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            }
            noResults.value -> {
                Text("No results found.", modifier = Modifier.padding(16.dp), color = Color.White)
            }
            else -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}