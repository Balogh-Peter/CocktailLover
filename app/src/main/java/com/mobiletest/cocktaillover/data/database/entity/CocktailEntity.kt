package com.mobiletest.cocktaillover.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cocktail")
data class CocktailEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,

    @ColumnInfo(name = "drinkId") val drinkId: String,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Category") val category: String,
    @ColumnInfo(name = "IsAlcoholic") val isAlcoholic: Boolean,
    @ColumnInfo(name = "RecommendedGlassType") val recommendedGlassType: String,
    @ColumnInfo(name = "Instructions") val instructions: String,
    @ColumnInfo(name = "PictureSource") val pictureSource: ByteArray

)