package com.mobiletest.cocktaillover.data.database.di

import android.content.Context
import com.mobiletest.cocktaillover.data.database.dao.CocktailDao
import com.mobiletest.cocktaillover.data.database.db.CocktailDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CocktaiLDatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): CocktailDatabase {
        return CocktailDatabase.getDatabase(applicationContext)
    }

    @Provides
    fun provideActivationDao(
        db: CocktailDatabase
    ): CocktailDao {
        return db.cocktailDao()
    }

}