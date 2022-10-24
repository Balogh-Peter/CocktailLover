package com.mobiletest.cocktaillover.data.di

import com.mobiletest.cocktaillover.data.api_interface.CocktailApi
import com.mobiletest.cocktaillover.data.repository.CocktailRepository
import com.mobiletest.cocktaillover.data.repository.CocktailRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRedditRepository(
        cocktailApi: CocktailApi
    ): CocktailRepository {
        return CocktailRepositoryImp(cocktailApi)
    }

}