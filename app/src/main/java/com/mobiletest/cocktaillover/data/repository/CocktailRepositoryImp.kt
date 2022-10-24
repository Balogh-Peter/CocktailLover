package com.mobiletest.cocktaillover.data.repository

import com.mobiletest.cocktaillover.data.api_interface.CocktailApi
import com.mobiletest.cocktaillover.domain.model.Cocktail
import javax.inject.Inject

class CocktailRepositoryImp @Inject constructor(
    private val cocktailApi: CocktailApi
) : CocktailRepository {

    override suspend fun getCocktails(): List<Cocktail> {

        val cocktails = arrayListOf<Cocktail>()
        val cocktailDTO = cocktailApi.getCocktails().body()

        cocktailDTO?.drinks?.forEach {
            cocktails.add(
                Cocktail(
                    drinkId = it.drinkId,
                    name = (it.name ?: ""),
                    category = (it.category ?: ""),
                    isAlcoholic = (it.isAlcoholic ?: ""),
                    recommendedGlassType = (it.recommendedGlassType ?: ""),
                    instructions = (it.instructions ?: ""),
                    thumbnailUrl = (it.thumbnailUrl ?: "")
                )
            )
        }

        return cocktails
    }

}