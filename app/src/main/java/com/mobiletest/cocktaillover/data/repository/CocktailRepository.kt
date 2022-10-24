package com.mobiletest.cocktaillover.data.repository

import com.mobiletest.cocktaillover.domain.model.Cocktail

interface CocktailRepository {

    suspend fun getCocktails(): List<Cocktail>

}