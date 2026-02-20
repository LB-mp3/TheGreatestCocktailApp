package fr.isen.bellini.thegreatestcocktailapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import fr.bellini.thegreatestcocktailapp.dataClasses.Drink
import fr.isen.bellini.thegreatestcocktailapp.network.RetrofitInstance
import fr.isen.bellini.thegreatestcocktailapp.screens.DetailCocktailScreen
import fr.isen.bellini.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class DetailCocktailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val drinkId = intent.getStringExtra("drinkId") ?: ""
        setContent {
            TheGreatestCocktailAppTheme {
                val cocktail = remember { mutableStateOf<Drink?>(null) }

                LaunchedEffect(drinkId) {
                    val response = RetrofitInstance.api.getDrinkDetail(drinkId)
                    cocktail.value = response.drinks?.first()
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (cocktail.value != null) {
                        DetailCocktailScreen(innerPadding, cocktail.value)
                    } else {
                        Text("Loading...")
                    }
                }
            }
        }
    }
}