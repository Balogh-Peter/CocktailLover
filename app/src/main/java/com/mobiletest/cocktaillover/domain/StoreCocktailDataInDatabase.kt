package com.mobiletest.cocktaillover.domain

import com.mobiletest.cocktaillover.data.database.dao.CocktailDao
import com.mobiletest.cocktaillover.data.database.entity.CocktailEntity
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import javax.inject.Inject

class StoreCocktailDataInDatabase @Inject constructor(
    private val cocktailDao: CocktailDao
) {

    suspend operator fun invoke(cocktails: List<CocktailWithPictureSource>) {

        val cocktailEntities = cocktails.map {
            CocktailEntity(
                drinkId = it.cocktail.drinkId,
                name = it.cocktail.name,
                category = it.cocktail.category,
                isAlcoholic = it.cocktail.isAlcoholic,
                recommendedGlassType = it.cocktail.recommendedGlassType,
                instructions = (it.cocktail.instructions),
                pictureSource = (it.byteArray)
            )
        }

        cocktailDao.addAll(cocktailEntities)

    }

}