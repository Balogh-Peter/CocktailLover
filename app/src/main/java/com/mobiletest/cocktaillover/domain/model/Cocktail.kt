package com.mobiletest.cocktaillover.domain.model

data class Cocktail(
    var drinkId: String,
    var name: String,
    var category: String,
    var isAlcoholic: String,

    var recommendedGlassType: String,
    var instructions: String,
    var thumbnailUrl: String
)