package com.mobiletest.cocktaillover.domain

import com.mobiletest.cocktaillover.data.database.dao.CocktailDao
import com.mobiletest.cocktaillover.data.network.repository.CocktailRepository
import com.mobiletest.cocktaillover.domain.model.Cocktail
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import java.net.URL
import javax.inject.Inject

class GetCocktailsWithPictureSources @Inject constructor(
    private val cocktailRepository: CocktailRepository,
    private val cocktailDao: CocktailDao,
    private val isNetworkAvailable: IsNetworkAvailable
) {

    suspend operator fun invoke(): List<CocktailWithPictureSource> {

        return if (isNetworkAvailable()) {
            getFromNetwork()
        } else {
            getFromDatabase()
        }

    }

    private suspend fun getFromNetwork(): ArrayList<CocktailWithPictureSource> {
        val cocktailWithPictureSources = arrayListOf<CocktailWithPictureSource>()
        val cocktails = cocktailRepository.getCocktails()

        cocktails.forEach {
            val pictureStream = URL(it.thumbnailUrl).openStream()
            val pictureByteArray = pictureStream.readBytes()
            cocktailWithPictureSources.add(
                CocktailWithPictureSource(pictureByteArray, it)
            )
        }

        if (cocktails.isNotEmpty()) {
            cocktailWithPictureSources.removeAt(0)
        }
        return cocktailWithPictureSources
    }

    private suspend fun getFromDatabase(): List<CocktailWithPictureSource> {
        val cocktailEntities = cocktailDao.getAll()
        val cocktails: List<CocktailWithPictureSource> = cocktailEntities.map {
            CocktailWithPictureSource(
                cocktail = Cocktail(
                    drinkId = it.drinkId,
                    name = it.name,
                    category = it.category,
                    isAlcoholic = it.isAlcoholic,
                    recommendedGlassType = it.recommendedGlassType,
                    instructions = (it.instructions),
                    thumbnailUrl = ""
                ),
                byteArray = it.pictureSource
            )
        }

        return cocktails
    }

}