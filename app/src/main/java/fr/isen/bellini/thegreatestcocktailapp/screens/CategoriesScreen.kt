package fr.isen.bellini.thegreatestcocktailapp.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.bellini.thegreatestcocktailapp.DrinksActivity

@Composable
fun CategoriesScreen(paddingValues: PaddingValues) {

    val context = LocalContext.current

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
        items(Category.allObjects()) { category ->
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(context, DrinksActivity::class.java)
                        intent.putExtra("category", Category.toString(category))
                        context.startActivity(intent)
                        Log.d("tag", "click on $category")
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x33FFFFFF)
                ),
                border = BorderStroke(1.dp, Color(0xAAFFFFFF)),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = Category.toString(category),
                    modifier = Modifier.padding(12.dp),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}