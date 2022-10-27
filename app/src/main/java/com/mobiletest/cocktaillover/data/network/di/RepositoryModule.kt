package com.mobiletest.cocktaillover.data.network.di

import com.mobiletest.cocktaillover.data.network.api_interface.CocktailApi
import com.mobiletest.cocktaillover.data.network.repository.CocktailRepository
import com.mobiletest.cocktaillover.data.network.repository.CocktailRepositoryImp
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