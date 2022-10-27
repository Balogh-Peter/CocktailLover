package com.mobiletest.cocktaillover.domain.model

import java.io.Serializable

data class CocktailWithPictureSource(
    var byteArray: ByteArray,
    var cocktail: Cocktail
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CocktailWithPictureSource
        if (!byteArray.contentEquals(other.byteArray)) return false
        if (cocktail != other.cocktail) return false
        return true
    }

    override fun hashCode(): Int {
        var result = byteArray.contentHashCode()
        result = 31 * result + cocktail.hashCode()
        return result
    }

}