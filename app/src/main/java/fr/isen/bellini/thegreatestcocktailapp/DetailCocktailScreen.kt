package fr.isen.bellini.thegreatestcocktailapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCocktailScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Cocktail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF5722),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favori",
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

            Image(
                painter = painterResource(id = R.drawable.imagecocktail),
                contentDescription = "Cocktail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)

            )


            Spacer(modifier = Modifier.height(16.dp))

            // Titre
            Text(
                text = "Cocktail Sunrise",
                style = MaterialTheme.typography.headlineMedium
            )

            // Catégorie
            Text("Catégorie : Classic")

            // Type de verre
            Text("Verre : Highball glass")

            Spacer(modifier = Modifier.height(16.dp))

            // Card ingrédients
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Ingrédients : ")
                    Text("- Vodka")
                    Text("- Jus de citron")
                    Text("- Jus d'orange")
                    Text("- Grenadine")

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card recette
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Recette : ")
                    Text( "Remplir un shaker de glaçons. Ajouter 4 cl de vodka, 2 cl de jus de citron frais et 6 cl de jus d'orange. Verser dans un verre highball rempli de glaçons, et ajouter doucement un peu de grenadine pour créer un effet dégradé. Servir bien frais!"
                            )
                }
            }
        }
    }
}
