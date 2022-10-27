package com.mobiletest.cocktaillover.data.network.repository

import com.mobiletest.cocktaillover.data.network.api_interface.CocktailApi
import com.mobiletest.cocktaillover.domain.model.Cocktail
import javax.inject.Inject

class CocktailRepositoryImp @Inject constructor(
    private val cocktailApi: CocktailApi
) : CocktailRepository {

    override suspend fun getCocktails(): List<Cocktail> {

        val cocktailDTO = cocktailApi.getCocktails().body()

        if (cocktailDTO != null) {
            return cocktailDTO.drinks.map {
                Cocktail(
                    drinkId = it.drinkId,
                    name = it.name ?: "",
                    category = it.category ?: "",
                    isAlcoholic = it.isAlcoholic == "Alcoholic",
                    recommendedGlassType = it.recommendedGlassType ?: "",
                    instructions = (it.instructions ?: ""),
                    thumbnailUrl = (it.thumbnailUrl ?: "")
                )
            }
        }

        return arrayListOf()
    }

}