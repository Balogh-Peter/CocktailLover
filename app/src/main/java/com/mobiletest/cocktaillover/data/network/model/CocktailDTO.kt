package com.mobiletest.cocktaillover.data.network.model

import com.google.gson.annotations.SerializedName

data class CocktailDTO(

    @SerializedName("drinks") var drinks: List<Drink>

) {

    data class Drink(
        @SerializedName("idDrink") var drinkId: String,
        @SerializedName("strDrink") var name: String? = null,
        @SerializedName("strCategory") var category: String? = null,
        @SerializedName("strAlcoholic") var isAlcoholic: String? = null,

        @SerializedName("strGlass") var recommendedGlassType: String? = null,
        @SerializedName("strInstructions") var instructions: String? = null,
        @SerializedName("strDrinkThumb") var thumbnailUrl: String? = null
    )

}