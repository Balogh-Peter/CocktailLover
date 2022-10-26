package com.mobiletest.cocktaillover.presentation.cocktail_list_screen

import android.graphics.Bitmap
import com.mobiletest.cocktaillover.domain.model.Cocktail

data class CocktailWithPictureBitmap(
    var bitmap: Bitmap,
    var cocktail: Cocktail
)