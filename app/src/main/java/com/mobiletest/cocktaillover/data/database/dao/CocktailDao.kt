package com.mobiletest.cocktaillover.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mobiletest.cocktaillover.data.database.entity.CocktailEntity

@Dao
interface CocktailDao {

    @Query("SELECT * FROM Cocktail WHERE id = :id")
    suspend fun getById(id: String): CocktailEntity

    @Query("SELECT * FROM Cocktail")
    suspend fun getAll(): List<CocktailEntity>

    @Insert
    suspend fun add(cocktail: CocktailEntity)

    @Insert
    suspend fun addAll(cocktails: List<CocktailEntity>)

    @Delete
    suspend fun delete(cocktail: CocktailEntity)

}