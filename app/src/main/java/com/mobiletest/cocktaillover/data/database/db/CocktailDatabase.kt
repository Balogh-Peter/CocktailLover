package com.mobiletest.cocktaillover.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiletest.cocktaillover.data.database.dao.CocktailDao
import com.mobiletest.cocktaillover.data.database.entity.CocktailEntity


@Database(entities = [CocktailEntity::class], version = 1)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao

    companion object {

        @Volatile
        private var INSTANCE: CocktailDatabase? = null

        fun getDatabase(appContext: Context): CocktailDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    appContext,
                    CocktailDatabase::class.java,
                    "cocktail_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}