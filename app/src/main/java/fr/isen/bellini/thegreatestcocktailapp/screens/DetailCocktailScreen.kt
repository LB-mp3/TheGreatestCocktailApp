package fr.isen.bellini.thegreatestcocktailapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.bellini.thegreatestcocktailapp.R

@Composable
fun DetailCocktailScreen(padding: PaddingValues) {

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        Image(
            painter = painterResource(id = R.drawable.cocktail_image),
            contentDescription = "Cocktail",
            contentScale = ContentScale.Crop,
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


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = "Cocktail",
                fontSize = 20.sp,
                modifier = Modifier
                    .background(
                        color = Color(0xFFFBCA9A),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Alcoholic",
                fontSize = 20.sp,
                modifier = Modifier
                    .background(
                        color = Color(0xFFFBCA9A),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                fontWeight = FontWeight.Medium
            )
        }

        Text("Verre : Highball glass")

        // Card Ingredients
        ElevatedCard(
            elevation = CardDefaults.cardElevation(10.dp),
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
            elevation = CardDefaults.cardElevation(6.dp),
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
