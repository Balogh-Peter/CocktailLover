package com.mobiletest.cocktaillover.domain

import com.mobiletest.cocktaillover.data.repository.CocktailRepository
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import java.net.URL
import javax.inject.Inject

class GetCocktailsWithPictureSources @Inject constructor(
    private val cocktailRepository: CocktailRepository
) {

    suspend operator fun invoke(): List<CocktailWithPictureSource> {

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

}