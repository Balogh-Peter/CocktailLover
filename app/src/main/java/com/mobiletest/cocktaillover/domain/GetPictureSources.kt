package com.mobiletest.cocktaillover.domain

import com.mobiletest.cocktaillover.data.repository.CocktailRepository
import com.mobiletest.cocktaillover.domain.model.PictureSource
import java.net.URL
import javax.inject.Inject

class GetPictureSources @Inject constructor(
    private val cocktailRepository: CocktailRepository
) {

    suspend operator fun invoke(): List<PictureSource> {

        val pictureSources = arrayListOf<PictureSource>()
        val cocktails = cocktailRepository.getCocktails()

        cocktails.forEach {
            val pictureStream = URL(it.thumbnailUrl).openStream()
            val pictureByteArray = pictureStream.readBytes()
            pictureSources.add(
                PictureSource(
                    pictureByteArray,
                    it.drinkId.toInt()
                )
            )
        }

        return pictureSources
    }

}