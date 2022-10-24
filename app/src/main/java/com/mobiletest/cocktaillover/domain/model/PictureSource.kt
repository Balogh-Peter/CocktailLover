package com.mobiletest.cocktaillover.domain.model

data class PictureSource(
    var byteArray: ByteArray,
    var drinkId: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PictureSource

        if (!byteArray.contentEquals(other.byteArray)) return false
        if (drinkId != other.drinkId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = byteArray.contentHashCode()
        result = 31 * result + drinkId
        return result
    }

}