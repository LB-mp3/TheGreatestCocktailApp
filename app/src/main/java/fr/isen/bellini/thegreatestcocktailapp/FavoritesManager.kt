package fr.isen.bellini.thegreatestcocktailapp

import android.content.Context

object FavoritesManager {

    private const val PREFS_NAME = "favorites"
    private const val KEY = "favorite_ids"

    fun getFavorites(context: Context): MutableSet<String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(KEY, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    }

    fun addFavorite(context: Context, drinkId: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favorites = getFavorites(context)
        favorites.add(drinkId)
        prefs.edit().putStringSet(KEY, favorites).apply()
    }

    fun removeFavorite(context: Context, drinkId: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favorites = getFavorites(context)
        favorites.remove(drinkId)
        prefs.edit().putStringSet(KEY, favorites).apply()
    }

    fun isFavorite(context: Context, drinkId: String): Boolean {
        return getFavorites(context).contains(drinkId)
    }
}