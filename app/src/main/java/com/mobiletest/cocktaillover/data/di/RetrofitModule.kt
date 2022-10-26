package com.mobiletest.cocktaillover.data.di

import com.mobiletest.cocktaillover.data.api_interface.CocktailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Named("BaseUrl")
    @Provides
    fun provideBaseUrl(): String {
        return "https://www.thecocktaildb.com/api/json/v1/1/"
    }

    @Provides
    fun provideRetrofitInstance(
        @Named("BaseUrl") baseUrl: String

    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCocktailApi(
        retrofit: Retrofit
    ): CocktailApi {
        return retrofit.create(CocktailApi::class.java)
    }


}