package fr.isen.bellini.thegreatestcocktailapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class Category {

    BEER,
    COCKTAIL,
    COCOA,
    COFFEE,
    LIQUOR,
    DRINK,
    PUNCH,
    SHAKE,
    SHOT,
    SOFT,
    ALCOHOLIC,
    NON_ALCOHOLIC,
    OTHER;

    companion object {

        fun allObjects(): List<Category> {
            return listOf(
                BEER,
                COCKTAIL,
                COCOA,
                COFFEE,
                LIQUOR,
                DRINK,
                PUNCH,
                SHAKE,
                SHOT,
                SOFT,
                OTHER
            )
        }

        fun toString(category: Category): String {
            return when (category) {
                ALCOHOLIC -> "Alcoholic"
                NON_ALCOHOLIC -> "Non alcoholic"
                OTHER -> "Other / Unknown"
                BEER -> "Beer"
                COCKTAIL -> "Cocktail"
                COCOA -> "Cocoa"
                COFFEE -> "Coffee / Tea"
                LIQUOR -> "Homemade Liqueur"
                DRINK -> "Ordinary Drink"
                PUNCH -> "Punch / Party Drink"
                SHAKE -> "Shake"
                SHOT -> "Shot"
                SOFT -> "Soft Drink"
            }
        }

        @Composable
        fun colors(category: Category): List<Color> {
            return listOf(
                Color(0xFFFBCA9A),
                Color(0xFFFBCA9A)
            )
        }
    }
}