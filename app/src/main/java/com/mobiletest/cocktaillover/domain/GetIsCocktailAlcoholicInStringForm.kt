package com.mobiletest.cocktaillover.domain

import javax.inject.Inject

class GetIsCocktailAlcoholicInStringForm @Inject constructor() {

    operator fun invoke(isAlcoholic: Boolean): String {
        return if (isAlcoholic) {
            "Alcoholic"
        } else {
            "Non-Alcoholic"
        }
    }

}