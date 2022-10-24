package com.mobiletest.cocktaillover.data.api_interface

import com.mobiletest.cocktaillover.data.model.CocktailDTO
import retrofit2.Response
import retrofit2.http.GET

interface CocktailApi {

    @GET("search.php?f=a")
    suspend fun getCocktails(): Response<CocktailDTO>

}