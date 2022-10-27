package com.mobiletest.cocktaillover.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.mobiletest.cocktaillover.data.database.dao.CocktailDao
import com.mobiletest.cocktaillover.data.database.db.CocktailDatabase
import com.mobiletest.cocktaillover.data.database.entity.CocktailEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CocktailDaoTest {

    private lateinit var cocktailDao: CocktailDao
    private lateinit var cocktailDatabase: CocktailDatabase
    private lateinit var cocktailEntity: CocktailEntity

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        cocktailDatabase = Room.inMemoryDatabaseBuilder(context, CocktailDatabase::class.java)
            .build()
        cocktailDao = cocktailDatabase.cocktailDao()
        cocktailEntity = CocktailEntity(
            drinkId = "1",
            name = "Test Name",
            category = "Test Category",
            isAlcoholic = true,
            recommendedGlassType = "Test Glass Type",
            instructions = "Test Instructions",
            pictureSource = ByteArray(0)
        )
    }

    @After
    fun tearDown() {
        cocktailDatabase.close()
    }

    @Test
    fun getById() = runTest {
        cocktailDao.add(cocktailEntity)

        cocktailDao.getById("1").let {
            Truth.assertThat(it).isNotNull()
        }
    }

    @Test
    fun getAll() = runTest {
        cocktailDao.addAll(
            listOf(
                cocktailEntity,
                cocktailEntity.copy(drinkId = "2")
            )
        )

        cocktailDao.getAll().let {
            Truth.assertThat(it).hasSize(2)
        }
    }

    @Test
    fun delete() = runTest {
        cocktailDao.add(cocktailEntity)

        cocktailDao.delete(cocktailEntity)

        cocktailDao.getAll().let {
            Truth.assertThat(it).isEmpty()
        }
    }

}